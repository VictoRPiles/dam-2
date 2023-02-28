# -*- coding: utf-8 -*-
# from odoo import http


# class FireStation(http.Controller):
#     @http.route('/fire_station/fire_station/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/fire_station/fire_station/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('fire_station.listing', {
#             'root': '/fire_station/fire_station',
#             'objects': http.request.env['fire_station.fire_station'].search([]),
#         })

#     @http.route('/fire_station/fire_station/objects/<model("fire_station.fire_station"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('fire_station.object', {
#             'object': obj
#         })
