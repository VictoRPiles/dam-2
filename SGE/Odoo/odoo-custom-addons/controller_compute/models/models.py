# -*- coding: utf-8 -*-

from odoo import models, fields


class Car(models.Model):
    _name = 'controller_compute.car'
    _description = 'Coche'

    name = fields.Char(string = 'Modelo')
    number_plate = fields.Char(string = 'Matrícula')
    cv = fields.Float(string = 'CV')
    colour = fields.Char(string = 'Color')
    fuel_litres = fields.Float(string = 'Litros')
    under_fuel = fields.Boolean(string = 'Necesita repostar', compute = '_compute_under_fuel')

    def _compute_under_fuel(self):
        if self.fuel_litres < 50:
            self.under_fuel = True
        else:
            self.under_fuel = False
