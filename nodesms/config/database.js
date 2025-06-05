const { Sequelize } = require('sequelize');

const sequelize = new Sequelize('nstudb', 'postgres', '8055', {
  host: 'localhost',
  dialect: 'postgres',
});

// Automatically sync models with DB
const connectDB = async () => {
  try {
    await sequelize.authenticate();
    console.log('Database connected.');

    await sequelize.sync({ alter: true }); 
    console.log('All models were synchronized successfully.');
  } catch (err) {
    console.error('Unable to connect to the database:', err);
  }
};

module.exports = { sequelize, connectDB };
