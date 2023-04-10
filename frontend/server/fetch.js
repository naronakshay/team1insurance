const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const mysql = require('mysql');

const app = express();


app.use(cors());
app.use(bodyParser.json());


const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'lookup'
});

connection.connect();


app.get('/states', (req, res) => {
  connection.query('SELECT * FROM state', (error, results, fields) => {
    if (error) {
      console.error(error);
      res.status(500).send('Error retrieving states');
    } else {
      res.json(results);
    }
  });
});

app.get('/disease', (req, res) => {
  connection.query('SELECT * FROM disease', (error, results, fields) => {
    if (error) {
      console.error(error);
      res.status(500).send('Error retrieving disease');
    } else {
      res.json(results);
    }
  });
});


app.get('/cities/:stateId', (req, res) => {
  const stateId = req.params.stateId;
  const query = 'SELECT * FROM city WHERE state_id = ?';
  connection.query(query, [stateId], (error, results) => {
    if (error) {
      throw error;
    }
    res.send(results);
  });
});







app.listen(3000, () => {
  console.log('Server started on port 3000');
});
