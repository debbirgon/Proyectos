import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Persona from './persona'
import Cuidador from './cuidador'

const Dependiente = sequelize.define(
	'Dependientes',
	{
		idDependientes: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idDependientes',
			allowNull: false,
			primaryKey: true
		},
		alias: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'alias'
		},
		persona: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Persona,
				key: 'idPersonas'
			},
			field: 'persona'
		},
		cuidador: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Cuidador,
				key: 'idCuidadores'
			},
			field: 'cuidador'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Dependiente