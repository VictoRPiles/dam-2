# -*- coding: utf-8 -*-
{
    'name': "odoo_model_advanced",

    'summary': """
        Conceptos avanzados de modelos""",

    'description': """
        Curso de conceptos avanzados de modelos
    """,

    'author': "Darío Rodríguez García",
    'website': "https://www.udemy.com/user/dario-rodriguez-garcia/",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/14.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Uncategorized',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        # 'security/ir.model.access.csv',
        'views/views.xml',
        'views/templates.xml',
    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
    ],
}
