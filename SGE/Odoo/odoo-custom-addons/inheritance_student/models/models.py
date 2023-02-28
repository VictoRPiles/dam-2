# -*- coding: utf-8 -*-

from datetime import date

from odoo import models, fields, api


class Person(models.Model):
    # ########## Odoo Fields ##########
    _name = 'inheritance_student.person'
    _description = 'Person'

    # ########## Class Fields ##########
    nif = fields.Char(string="NIF", required=True)
    name = fields.Char(string="Name", required=True)
    surname1 = fields.Char(string="First surname", required=True)
    surname2 = fields.Char(string="Second surname", required=True)
    birthday = fields.Date(string="Birthday", required=True)
    age = fields.Integer(string="Age", compute="_compute_age", store=False)

    @api.depends('birthday')
    def _compute_age(self):
        print("[Person] _compute_age")

        today = date.today()
        for record in self:
            birthday = record.birthday
            name = record.name

            # No birthday -> exit case
            if not birthday:
                print("\t" + str(name) + ": birthday = None")
                print("\t" + str(name) + ": age = None")
                record.age = None
                break

            print("\t" + str(name) + ": birthday = " + str(birthday))
            age = today.year - birthday.year - ((today.month, today.day) < (birthday.month, birthday.day))
            record.age = age
            print("\t" + str(name) + ": age = " + str(age))


class Student(models.Model):
    # ########## Odoo Fields ##########
    _name = 'inheritance_student.student'
    _description = 'Student'
    _inherit = "inheritance_student.person"

    # ########## Class Fields ##########
    average_grade = fields.Float(string='Average grade')

    # ########## Foreign Keys ##########
    classroom_id = fields.Many2one(comodel_name='inheritance_student.classroom', string='Classroom')


class Teacher(models.Model):
    # ########## Odoo Fields ##########
    _name = 'inheritance_student.teacher'
    _description = 'Teacher'
    _inherit = "inheritance_student.person"

    # ########## Class Fields ##########
    salary = fields.Float(string='Salary')

    # ########## Foreign Keys ##########
    classroom_ids = fields.Many2many(
        'inheritance_student.classroom',
        'inheritance_student_classroom_teacher_rel',
        'teacher_id',
        'classroom_id',
        string='Classrooms')


class Classroom(models.Model):
    # ########## Odoo Fields ##########
    _name = 'inheritance_student.classroom'
    _description = 'Classroom'

    # ########## Class Fields ##########
    name = fields.Char(string="Name", required=True)

    # ########## Foreign Keys ##########
    student_ids = fields.One2many(comodel_name='inheritance_student.student', inverse_name='classroom_id',
                                  string='Students')
    teacher_ids = fields.Many2many(
        'inheritance_student.teacher',
        'inheritance_student_classroom_teacher_rel',
        'classroom_id',
        'teacher_id',
        string='Teachers'
    )
