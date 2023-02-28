# -*- coding: utf-8 -*-

from odoo import models, fields, api


class Car(models.Model):
    _name = 'controller_depends.car'
    _description = 'Coche'

    name = fields.Char(string = 'Modelo')
    number_plate = fields.Char(string = 'Matrícula')
    cv = fields.Float(string = 'CV')
    colour = fields.Char(string = 'Color')
    fuel_litres = fields.Float(string = 'Litros', compute = '_check_under_fuel')
    under_fuel = fields.Boolean(string = 'Necesita repostar')
    customer = fields.Many2one(comodel_name = 'res.users', string = 'Cliente')

    @api.depends('fuel_litres')
    def _check_under_fuel(self):
        print('Se ejecuta depends...')
        if self.fuel_litres < 50:
            self.under_fuel = True
        else:
            self.under_fuel = False
