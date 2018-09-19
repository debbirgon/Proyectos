import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

const Persona = sequelize.define(
	'PERSONAS',
	{
		ID: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'ID',
			allowNull: false,
			primaryKey: true
		},
		DNI: {
			type: Sequelize.STRING(9),
			allowNull: false,
			field: 'DNI'
		},
		NOMBRE: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'NOMBRE'
		},
		APELLIDOS: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'APELLIDOS'
		},
		FECHA_NACIMIENTO: {
			type: Sequelize.DATE,
			allowNull: false,
			field: 'FECHA_NACIMIENTO'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Persona