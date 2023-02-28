# -*- coding: utf-8 -*-

from odoo import models, fields, api


class Bombero(models.Model):
    # ########## Campos Odoo ##########
    _name = 'piles.bombero'
    _description = 'Piles Bombero'
    _order = 'apellido1'

    # ########## Campos Classe ##########
    name = fields.Char(string = 'Nombre', required = True)
    apellido1 = fields.Char(string = 'Primer apellido')
    apellido2 = fields.Char(string = 'Segundo apellido')
    nacmiento = fields.Date(string = 'Fecha de nacimiento')
    salario = fields.Float(string = 'Salario')
    antiguedad = fields.Integer(string = 'Años de antiguedad')
    carnet = fields.Boolean(string = 'Tiene carnet camion')
    numero_carnet = fields.Char(string = 'Numero de carnet')

    # ########## Foreign Keys ##########
    nacionalidad = fields.Many2one(comodel_name = 'res.country', string = 'Nacionalidad')
    parque_id = fields.Many2one(comodel_name = 'piles.parque', string = 'Parque')
    bomberocamion_ids = fields.One2many(comodel_name = 'piles.bomberocamion', inverse_name = 'bombero_id',
                                        string = 'Bombero Camion')


class Camion(models.Model):
    # ########## Campos Odoo ##########
    _name = 'piles.camion'
    _description = 'Piles Camion'

    # ########## Campos Clase ##########
    matricula = fields.Char(string = 'Matricula', required = True)
    modelo = fields.Char(string = 'Modelo')
    cv = fields.Integer(string = 'CV')
    combustible = fields.Integer(string = 'Combustible')
    debe_repostar = fields.Boolean(string = 'Debe repostar', compute = "_compute_debe_repostar", store = False)
    capacidad_agua = fields.Integer(string = 'Capacidad de agua')
    escalera = fields.Boolean(string = 'Tiene escalera')
    en_taller = fields.Boolean(string = 'En taller')
    ruedas = fields.Selection([('4', '4'), ('6', '6')], string = 'Numero de ruedas', default = '4')

    # ########## Foreign Keys ##########
    parque_id = fields.Many2one(comodel_name = 'piles.parque', string = 'Parque')
    bomberocamion_ids = fields.One2many(comodel_name = 'piles.bomberocamion', inverse_name = 'camion_id',
                                        string = 'Bombero Camion')

    # Verificar que la matricula sea unica
    _sql_constraints = [
        ('matricula_unique_constraint', 'UNIQUE(matricula)', 'La matricula ya existe')
    ]

    # @Override
    @api.depends('matricula')
    def name_get(self):
        """
        Vista de calendar de compra mostrando el name, generado igual que hacíamos con name_get() en clase .
        """
        print("[Camion] name_get")

        result = []
        for record in self:
            if record.matricula:
                camion_name = record.matricula
                name = str(camion_name)
                print("\t" + name)
                camion_id = record.id
                result.append((camion_id, name))
        return result

    @api.depends('combustible')
    def _compute_debe_repostar(self):
        print("[Camion] _compute_debe_repostar")

        for record in self:
            combustible = record.combustible
            # No combustible -> exit case
            deben_repostar = record.parque_id.camiones_deben_repostar
            if not combustible:
                print("\tCombustible no definido")
                record.debe_repostar = True

                print(deben_repostar)
                record.parque_id.camiones_deben_repostar = (deben_repostar + 1)
                print(deben_repostar)

                break

            print("\tCombustible definido")
            if combustible < 15:
                print("\tCombustible bajo")
                record.debe_repostar = True

                print(deben_repostar)
                record.parque_id.camiones_deben_repostar = (deben_repostar + 1)
                print(deben_repostar)

                print("\tDebe repostar")
            else:
                record.debe_repostar = False

                print(deben_repostar)
                record.parque_id.camiones_deben_repostar = (deben_repostar + 1)
                print(deben_repostar)

                print("\tNo debe repostar")


class BomberoCamion(models.Model):
    # ########## Campos Odoo ##########
    _name = 'piles.bomberocamion'
    _description = 'Piles Bombero - Camion'

    # ########## Campos Clase ##########
    fecha_inicio = fields.Date(string = 'Fecha inicio')
    fecha_fin = fields.Date(string = 'Fecha fin')
    puesto = fields.Selection([('pasajero', 'Pasajero'), ('conductor', 'Conductor')], string = 'Puesto',
                              default = 'pasajero')

    # ########## Foreign Keys ##########
    bombero_id = fields.Many2one(comodel_name = 'piles.bombero', string = 'Bombero')
    camion_id = fields.Many2one(comodel_name = 'piles.camion', string = 'Camion')

    # @Override
    @api.depends('bombero_id', 'camion_id')
    def name_get(self):
        """
        Vista de calendar de compra mostrando el name, generado igual que hacíamos con name_get() en clase .
        """
        print("[BomberoCamion] name_get")

        result = []
        for record in self:
            if record.bombero_id.name and record.camion_id.matricula:
                bombero_id_name = record.bombero_id.name
                camion_id_matricula = record.camion_id.matricula
                name = str(bombero_id_name) + " " + str(camion_id_matricula)
                print("\t" + name)
                bomberocamion_id = record.id
                result.append((bomberocamion_id, name))
        return result


class Parque(models.Model):
    # ########## Campos Odoo ##########
    _name = 'piles.parque'
    _description = 'Piles Parque'

    # ########## Campos Clase ##########
    name = fields.Char(string = 'Nombre', required = True)
    direccion = fields.Char(string = 'Direccion')
    capacidad_bomberos = fields.Integer(string = 'Capacidad de bomberos')
    capacidad_camiones = fields.Integer(string = 'Capacidad de camiones')
    camiones_deben_repostar = fields.Integer(string = 'Camiones deben repostar', default = 0)

    # ########## Foreign Keys ##########
    pais = fields.Many2one(comodel_name = 'res.country', string = 'Pais')
    bombero_ids = fields.One2many(comodel_name = 'piles.bombero', inverse_name = 'parque_id', string = 'Bomberos')
    camion_ids = fields.One2many(comodel_name = 'piles.camion', inverse_name = 'parque_id', string = 'Camiones')
