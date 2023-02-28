# -*- coding: utf-8 -*-
# from odoo import http


# class Cesta-compra(http.Controller):
#     @http.route('/shopping_cart/shopping_cart/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/shopping_cart/shopping_cart/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('shopping_cart.listing', {
#             'root': '/shopping_cart/shopping_cart',
#             'objects': http.request.env['shopping_cart.shopping_cart'].search([]),
#         })

#     @http.route('/shopping_cart/shopping_cart/objects/<model("shopping_cart.shopping_cart"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('shopping_cart.object', {
#             'object': obj
#         })
