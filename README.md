# Template Engine


## Sample
### Input
#### Data
````json
{
  "subjects":[
    {
      "name": "sport",
      "quizzes":[
        {
          "number": "1",
            "question":"Which one is correct team name in NBA?",
            "options":[
              "New York Bulls",
              "Los Angeles Kings",
              "Golden State Warriros",
              "Huston Rocket"
            ],
            "answer":"Huston Rocket"
          }
      ]
    },
    {
      "name": "maths",
      "quizzes":[
        {
          "number": "1",
            "question": "5 + 7 = ?",
            "options": [
              "10",
              "11",
              "12",
              "13"
            ],
            "answer": "12"
        },{
          "number": "2",
            "question":"12 - 8 = ?",
            "options":[
              "1",
              "2",
              "3",
              "4"
            ],
            "answer":"4"
        }
      ]
    }
  ]
}
````

#### Template
````
Quiz
---------------------------------------
<? for SUBJECT in QUIZ.subjects ?>
Subject name: <?= SUBJECT.name ?>
  <? for Q in SUBJECT.quizzes ?>
    Q<?= Q.number ?>. <?= Q.question ?>
      <? for OPTION in Q.options ?>
        - [ ] <?= OPTION ?>
      <? endfor ?>

  <? endfor ?>
---------------------------------------
<? endfor ?>
````

### Output
````
Quiz
---------------------------------------
Subject name: sport
    Q1. Which one is correct team name in NBA?
        - [ ] New York Bulls
        - [ ] Los Angeles Kings
        - [ ] Golden State Warriros
        - [ ] Huston Rocket

---------------------------------------
Subject name: maths
    Q1. 5 + 7 = ?
        - [ ] 10
        - [ ] 11
        - [ ] 12
        - [ ] 13

    Q2. 12 - 8 = ?
        - [ ] 1
        - [ ] 2
        - [ ] 3
        - [ ] 4

---------------------------------------
````