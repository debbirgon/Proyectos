import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

const Perfil = sequelize.define(
	'Perfiles',
	{
		idPerfiles: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idPerfiles',
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
	}
)

module.exports = Perfil