const express = require('express');
const router = express.Router();

const subjectController = require('../controllers/subjectController');

router.post('/create', subjectController.createSubject);
router.get('/:id', subjectController.getSubjectById);
router.get('/getAll', subjectController.getAllSubjects);
router.put('/:id', subjectController.updateSubject);

module.exports = router;
