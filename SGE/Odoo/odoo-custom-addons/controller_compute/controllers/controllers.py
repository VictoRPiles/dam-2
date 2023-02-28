# -*- coding: utf-8 -*-
# from odoo import http


# class ControllerCompute(http.Controller):
#     @http.route('/controller_compute/controller_compute/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/controller_compute/controller_compute/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('controller_compute.listing', {
#             'root': '/controller_compute/controller_compute',
#             'objects': http.request.env['controller_compute.controller_compute'].search([]),
#         })

#     @http.route('/controller_compute/controller_compute/objects/<model("controller_compute.controller_compute"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('controller_compute.object', {
#             'object': obj
#         })
