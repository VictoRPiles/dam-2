# -*- coding: utf-8 -*-
# from odoo import http


# class ControllerConstraint(http.Controller):
#     @http.route('/controller_constraint/controller_constraint/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/controller_constraint/controller_constraint/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('controller_constraint.listing', {
#             'root': '/controller_constraint/controller_constraint',
#             'objects': http.request.env['controller_constraint.controller_constraint'].search([]),
#         })

#     @http.route('/controller_constraint/controller_constraint/objects/<model("controller_constraint.controller_constraint"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('controller_constraint.object', {
#             'object': obj
#         })
