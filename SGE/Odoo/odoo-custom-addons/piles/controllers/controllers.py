# -*- coding: utf-8 -*-
# from odoo import http


# class Piles(http.Controller):
#     @http.route('/piles/piles/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/piles/piles/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('piles.listing', {
#             'root': '/piles/piles',
#             'objects': http.request.env['piles.piles'].search([]),
#         })

#     @http.route('/piles/piles/objects/<model("piles.piles"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('piles.object', {
#             'object': obj
#         })
