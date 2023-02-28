# -*- coding: utf-8 -*-

from datetime import date

from odoo import models, fields, api
from odoo.exceptions import ValidationError


class Inventory(models.Model):
    # ########## Odoo Fields ##########
    _name = 'shopping_cart_security.inventory'
    _description = 'shopping_cart_security.inventory'
    # Debe de mostrarse los productos en la vista de árbol del inventario ordenado por precio (_order).
    _order = 'price_per_unit desc'

    # ########## Class Fields ##########
    name = fields.Char(string = "Name", required = True)
    quantity = fields.Integer(string = "Quantity", required = True)
    product_type = fields.Char(string = "Product type")
    price_per_unit = fields.Float(string = "Price per unit", required = True)

    # ########## Foreign Keys ##########
    buy_ids = fields.One2many(comodel_name = 'shopping_cart_security.purchase', inverse_name = 'inventory_id',
                              string = 'Purchases')

    # La clase inventario debe comprobar que al insertar un nuevo producto en el sistema, el nombre no está ya
    # utilizado (sql_constraints).
    _sql_constraints = [
        ('name_unique_constraint', 'UNIQUE(name)', 'Product name already exists !')
    ]


class Client(models.Model):
    # ########## Odoo Fields ##########
    _name = 'shopping_cart_security.client'
    _description = 'shopping_cart_security.client'

    # ########## Class Fields ##########
    # Añadir un campo NIF.
    nif = fields.Char(string = "NIF", required = True)
    nif_tutor = fields.Char(string = "NIF Tutor")
    name = fields.Char(string = "Name", required = True)
    surname1 = fields.Char(string = "First surname", required = True)
    surname2 = fields.Char(string = "Second surname", required = True)
    address = fields.Char(string = "Address")
    # Añadir un campo fecha de nacimiento.
    birthday = fields.Date(string = "Birthday", required = True)
    # Mostrar la edad
    age = fields.Integer(string = "Age", compute = "_compute_age", store = False)
    # Mostrar si és mayor de edad
    adult = fields.Boolean(string = "Adult", default = False, compute = "_compute_adult", store = False)
    # Mostrar el dinero que lleva gastado un comprador
    spent_money = fields.Float(string = "Spent money", compute = "_compute_spent_money", store = False)
    # Tiene un límite de dinero a cuenta.
    money_limit = fields.Float(string = "Money limit")

    # ########## Foreign Keys ##########
    # Añadir un campo del país del comprador, obtendrá la información de la tabla res_country del sistema.
    country_id = fields.Many2one(comodel_name = 'res.country', string = 'Country')
    purchase_ids = fields.One2many(comodel_name = 'shopping_cart_security.purchase', inverse_name = 'client_id',
                                   string = 'Purchases')

    # No puede repetirse el NIF.
    _sql_constraints = [
        ('nif_unique_constraint', 'UNIQUE(nif)', 'NIF already exists !')
    ]

    @api.depends('birthday')
    def _compute_age(self):
        """
        Mostrar la edad.
        """
        print("[Client] _compute_age")

        today = date.today()
        for record in self:
            birthday = record.birthday
            name = record.name

            # No birthday -> exit case
            if not birthday:
                print("\t" + str(name) + ": birthday = None")
                print("\t" + str(name) + ": age = None")
                record.age = None
                break

            print("\t" + str(name) + ": birthday = " + str(birthday))
            age = today.year - birthday.year - ((today.month, today.day) < (birthday.month, birthday.day))
            record.age = age
            print("\t" + str(name) + ": age = " + str(age))

    @api.depends('birthday')
    def _compute_adult(self):
        """
        Mostrar si és mayor de edad.
        """
        print("[Client] _compute_adult")

        today = date.today()
        for record in self:
            birthday = record.birthday
            name = record.name

            # No birthday -> exit case
            if not birthday:
                print("\t" + str(name) + ": birthday = None")
                print("\t" + str(name) + " is not an adult")
                record.adult = False
                break

            print("\t" + str(name) + ": birthday = " + str(birthday))
            age = today.year - birthday.year - ((today.month, today.day) < (birthday.month, birthday.day))

            if age >= 18:
                print("\t" + str(name) + " is an adult")
                record.adult = True
            else:
                print("\t" + str(name) + " is not an adult")
                record.adult = False

    @api.depends('purchase_ids')
    def _compute_spent_money(self):
        print("[Client] _compute_spent_money")

        for record in self:
            # Reset the spent money
            total_spent_money = 0
            purchase_ids = record.purchase_ids
            # No purchases -> exit case
            if not purchase_ids:
                print("\t" + str(record.name) + ": no purchases")
                record.spent_money = 0
                break

            for purchase in purchase_ids:
                # Calculate the cost
                spent_in_purchase = purchase.inventory_id.price_per_unit * purchase.quantity

                print("\t" + str(purchase.client_id.name) + " bought " + str(purchase.quantity) + " units of " + str(
                    purchase.inventory_id.name) + " for " + str(
                    purchase.inventory_id.price_per_unit) + "€ each, a total of: " + str(spent_in_purchase) + "€")

                # Add to the total
                total_spent_money += spent_in_purchase

            record.spent_money = total_spent_money

    @api.constrains('spent_money')
    def _money_limit_exceeded_constrain(self):
        print("[Client] _money_limit_exceeded_constrain")

        for record in self:
            spent_money = record.spent_money
            money_limit = record.money_limit
            name = record.name
            if spent_money > money_limit:
                message = str(name) + ": money limit exceeded !"
                print(message)
                raise ValidationError(message)
            else:
                message = str(name) + ": money limit not exceeded !"
                print(message)


class Purchase(models.Model):
    # ########## Odoo Fields ##########
    _name = 'shopping_cart_security.purchase'
    _description = 'shopping_cart_security.purchase'
    # Ordenar las compras por cliente, y fecha.
    _order = 'client_id, date'

    # ########## Class Fields ##########
    quantity = fields.Integer(default = 0, string = "Quantity", required = True)
    date = fields.Date(string = "Date")
    # Poder indicar si compramos a cuenta o con tarjeta de crédito.
    pay_method = fields.Selection([('card', 'Credit card'), ('cash', 'Cash')], string = "Pay Method", default = "card")

    # ########## Foreign Keys ##########
    # No se podrá seleccionar un producto que no tenga stock.
    inventory_id = fields.Many2one(comodel_name = 'shopping_cart_security.inventory', string = 'Inventory',
                                   required = True, domain = [('quantity', '>', '0')])
    client_id = fields.Many2one(comodel_name = 'shopping_cart_security.client', string = 'Client', required = True)

    @api.constrains('inventory_id', 'quantity')
    def _check_stock(self):
        """
        No se podrá seleccionar un producto que no tenga stock.
        No podemos comprar más productos de los que tenemos en el inventario.
        """
        print("[Purchase] _check_stock")

        quantity = self.quantity
        inventory_id_name = self.inventory_id.name
        client_id_name = self.client_id.name
        # No quantity -> exit case
        if quantity <= 0:
            message = "\t" + str(inventory_id_name) + " " + str(client_id_name) + ": no quantity bought"
            print(message)
            raise ValidationError(message)

        inventory_id_quantity = self.inventory_id.quantity
        # No stock -> exit case
        if inventory_id_quantity < 0:
            message = "\t" + str(inventory_id_name) + " " + str(client_id_name) + ": no stock of " + str(
                inventory_id_name)
            print(message)
            raise ValidationError(message)

        # Not enough stock -> exit case
        if inventory_id_quantity < quantity:
            message = "\t" + str(inventory_id_name) + " " + str(client_id_name) + ": not enough stock: " + str(
                quantity) + " bought, but " + str(inventory_id_quantity) + " in stock"
            print(message)
            raise ValidationError(message)

        # Successful purchase
        message = "\t" + str(inventory_id_name) + " " + str(client_id_name) + ": enough stock: " + str(
            quantity) + " bought, but " + str(inventory_id_quantity) + " in stock"
        print(message)
        self.decrease_stock()

    # @Override
    @api.depends('inventory_id', 'client_id')
    def name_get(self):
        """
        Vista de calendar de compra mostrando el name, generado igual que hacíamos con name_get() en clase .
        """
        print("[Purchase] name_get")

        result = []
        for purchase in self:
            if purchase.inventory_id.name and purchase.client_id.name:
                inventory_id_name = purchase.inventory_id.name
                client_id_name = purchase.client_id.name
                name = str(inventory_id_name) + " " + str(client_id_name)
                print("\t" + name)
                purchase_id = purchase.id
                result.append((purchase_id, name))
        return result

    def decrease_stock(self):
        """
        Cuando se realiza una compra deberemos de descontar la cantidad de producto comprado de la tabla de inventario.
        """
        print("[Purchase] decrease_stock")

        inventory_id_quantity = self.inventory_id.quantity
        inventory_id_name = self.inventory_id.name
        client_id_name = self.client_id.name
        quantity = self.quantity

        print("\t" + str(inventory_id_name) + " " + str(client_id_name) +
              ": Old quantity: " + str(inventory_id_quantity))
        print("\t" + str(inventory_id_name) + " " + str(client_id_name) +
              ": Bought quantity: " + str(quantity))

        self.inventory_id.quantity = inventory_id_quantity - quantity
        print("\t" + str(inventory_id_name) + " " + str(client_id_name) +
              ": New quantity: " + str(inventory_id_quantity - quantity))
