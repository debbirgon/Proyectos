import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

const Perfil = sequelize.define(
	'perfiles',
	{
		idperfiles: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idperfiles',
			allowNull: false,
			primaryKey: true
		},
		nombre: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'nombre'
		},
		descripcion: {
			type: Sequelize.STRING(45),
			allowNull: true,
			field: 'descripcion'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}, {
		getterMethods: {
			fullName: function () {
				return this.getDataValue('nombre') + ' y ' + this.getDataValue('descripcion')
			}
		},
		setterMethods: {
			fullName: function (value) {
				var parts = value.split(' ')
	
				this.setDataValue('lastName', parts[parts.length-1])
				this.setDataValue('firstName', parts[0]) // this of course does not work if the user has several first names
			}
		}
	}
)

module.exports = Perfil