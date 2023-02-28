# -*- coding: utf-8 -*-
from odoo import models, fields, api


class Car(models.Model):
    _name = 'controller_onchange.car'
    _description = 'Coche'

    name = fields.Char(string = 'Modelo')
    number_plate = fields.Char(string = 'Matrícula')
    cv = fields.Float(string = 'CV')
    colour = fields.Char(string = 'Color')
    fuel_litres = fields.Float(string = 'Litros')
    under_fuel = fields.Boolean(string = 'Necesita repostar', compute = '_check_under_fuel')

    @api.onchange('fuel_litres')
    def _check_under_fuel(self):
        for record in self:
            if record.fuel_litres < 50:
                record.under_fuel = True
            else:
                record.under_fuel = False
