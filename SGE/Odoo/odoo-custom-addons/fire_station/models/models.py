# -*- coding: utf-8 -*-
from datetime import date

from dateutil.relativedelta import relativedelta

from odoo import models, fields, api


class Fireman(models.Model):
    _name = 'fire_station.fireman'
    _description = 'Fireman'

    name = fields.Char(string = 'Name')
    surname = fields.Char(string = 'Surname')
    age = fields.Integer(string = 'Age', compute = '_age_compute')
    birthday = fields.Date(string = 'Birthday')
    salary = fields.Float(string = 'Salary')
    job = fields.Selection([('driver', 'Driver'), ('passenger', 'Passenger')])

    truck_id = fields.Many2one(comodel_name = 'fire_station.truck', string = 'Truck',
                               domain = [('damaged', '=', False)])

    @api.depends('birthday')
    def _age_compute(self):
        today = date.today()
        for record in self:
            record.age = relativedelta(today, record.birthday).years
            print("[Fireman] Calculated age: " + str(record.age))


class Truck(models.Model):
    _name = 'fire_station.truck'
    _description = 'Truck'
    _order = 'damaged'

    number_plate = fields.Char(string = 'Number of plate')
    year = fields.Date(string = 'Starting date')
    wheels = fields.Integer(string = 'Number of wheels')
    capacity = fields.Integer(string = 'Fireman capacity')
    kilometers = fields.Float(string = 'Kilometers')
    damaged = fields.Boolean(string = 'Damaged', default = False)

    station_id = fields.Many2one(comodel_name = 'fire_station.station', string = 'Station')
    fireman_ids = fields.One2many(comodel_name = 'fire_station.fireman', inverse_name = 'truck_id', string = 'Firemen')


class Station(models.Model):
    _name = 'fire_station.station'
    _description = 'Station'

    name = fields.Char(string = 'Name')
    year = fields.Date(string = 'Starting date')
    city = fields.Char(string = "City")

    truck_ids = fields.One2many(comodel_name = 'fire_station.truck', inverse_name = 'station_id', string = 'Trucks')
