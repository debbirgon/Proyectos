import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Perfil from './perfil'
import Cuidador from './cuidador'

const Usuario = sequelize.define(
	'Usuarios',
	{
		idUsuario: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idUsuario',
			allowNull: false,
			primaryKey: true
		},
		username: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'username'
		},
		password: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'password'
		},
		cuidador_id: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Cuidador,
				key: 'idCuidadores'
			},
			field: 'cuidador_id'
		},
		E_perfil: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Perfil,
				key: 'idPerfiles'
			},
			field: 'E_perfil'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Usuario