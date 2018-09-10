import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

const Persona = sequelize.define(
	'Personas',
	{
		idPersonas: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idPersonas',
			allowNull: false,
			primaryKey: true
		},
		dni: {
			type: Sequelize.STRING(9),
			allowNull: false,
			field: 'dni'
		},
		nombre: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'nombre'
		},
		apellidos: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'apellidos'
		},
		fecha_nacimiento: {
			type: Sequelize.DATE,
			allowNull: false,
			field: 'fecha_nacimiento'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Persona