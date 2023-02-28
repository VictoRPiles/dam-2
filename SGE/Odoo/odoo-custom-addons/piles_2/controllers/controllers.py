# -*- coding: utf-8 -*-
# from odoo import http


# class Piles2(http.Controller):
#     @http.route('/piles_2/piles_2/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/piles_2/piles_2/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('piles_2.listing', {
#             'root': '/piles_2/piles_2',
#             'objects': http.request.env['piles_2.piles_2'].search([]),
#         })

#     @http.route('/piles_2/piles_2/objects/<model("piles_2.piles_2"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('piles_2.object', {
#             'object': obj
#         })
