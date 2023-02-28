# -*- coding: utf-8 -*-

from odoo import models, fields, api


class Parque(models.Model):
    _name = 'piles_2.parque'
    _inherit = 'piles.parque'
    diputacion_ids = fields.Many2one('piles_2.diputacion', string = 'Diputacion')


class Diputacion(models.Model):
    _name = 'piles_2.diputacion'
    _description = 'Piles 2 Diputacion'

    name = fields.Char(string = "Nombre", required = True)
    parque_ids = fields.One2many('piles_2.parque', 'diputacion_ids', string = 'Parques')
