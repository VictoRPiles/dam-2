# -*- coding: utf-8 -*-
from odoo import models, fields, api, exceptions


class Car(models.Model):
    _name = 'controller_constraint.car'
    _description = 'Coche'

    name = fields.Char(string = 'Modelo')
    number_plate = fields.Char(string = 'Matrícula')
    cv = fields.Float(string = 'CV')
    colour = fields.Char(string = 'Color')
    fuel_litres = fields.Float(string = 'Litros')

    @api.constrains('cv')
    def _validate_cv(self):
        if self.cv <= 0:
            raise exceptions.ValidationError('El valor de CV no puede ser menor o igual a 0')
