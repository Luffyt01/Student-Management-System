const express = require('express');
const { connectDB } = require('./config/database');

const courseRoutes = require('./routes/courseRoutes');
const studentRoutes = require('./routes/studentRoutes');
const subjectRoutes = require('./routes/subjectRoutes');

const app = express();
app.use(express.json());

app.use('/courses', courseRoutes);
app.use('/students', studentRoutes);
app.use('/subjects', subjectRoutes);

connectDB().then(() => {
  app.listen(3000, () => {
    console.log('Server running on port 3000');
  });
});
