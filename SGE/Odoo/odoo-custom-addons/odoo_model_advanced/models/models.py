# -*- coding: utf-8 -*-
from odoo import models, fields


class Car(models.Model):
    _name = 'odoo_model_advanced.car'
    _description = 'Coche'

    name = fields.Char(string = 'Modelo')
    number_plate = fields.Char(string = 'Matrícula')
    cv = fields.Float(string = 'CV')
    colour = fields.Char(string = 'Color')
    fuel_litres = fields.Float(string = 'Litros')

    new = fields.Boolean(string = 'Nuevo')
    text = fields.Char(string = 'Descripcion')
    img = fields.Image(string = 'Imagen')
    type = fields.Selection(
        [
            ('trabajo', 'Trabajo'),
            ('particular', 'Particular'),
            ('alquiler', 'Alquiler')
        ], string = 'Tipo'
    )
    # Nando
