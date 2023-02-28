# -*- coding: utf-8 -*-
# from odoo import http


# class ControllerDepends(http.Controller):
#     @http.route('/controller_depends/controller_depends/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/controller_depends/controller_depends/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('controller_depends.listing', {
#             'root': '/controller_depends/controller_depends',
#             'objects': http.request.env['controller_depends.controller_depends'].search([]),
#         })

#     @http.route('/controller_depends/controller_depends/objects/<model("controller_depends.controller_depends"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('controller_depends.object', {
#             'object': obj
#         })
