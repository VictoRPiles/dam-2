# -*- coding: utf-8 -*-
{
    'name': "controller_onchange",

    'summary': """
        """,

    'description': """  """,
    'author': "",
    'website': "",

    # Categories can be used to filter modules in modules listing
    # Check
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
