using ExerciseGame.Data;


using ExerciseGame.Data.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExerciseGame.Core.Randoms
{

    protected abstract class Exercise
    {

        private Random rnd = new Random();
        public virtual List<string> Actions
        {
            get
            {
                return new List<string> { "+", "-", "/", "*"  };
            }
         
        }

        public virtual string ExerciseQuestion { get; protected set; }
        public virtual List<string> ExerciseList { get; protected set; }


        public virtual string Action()
        {
           
            string action = " ";
            int randomNum = rnd.Next(0, Actions.Count);

            action = Actions[randomNum];
     
            return action;
        }

        public abstract string Changeable();

        public virtual string Count(string leftNum, string action, string rightNum)
        {
            double count = 0;
            switch (action)
            {
                case "+":
                    count = CalculateManager.Calculate(Convert.ToDouble(leftNum), Convert.ToDouble(rightNum),(x,y) => x + y);
                    break;
                case "-":
                    count = CalculateManager.Calculate(Convert.ToDouble(leftNum), Convert.ToDouble(rightNum), (x, y) => x - y);
                    break;
                case "*":
                    count = CalculateManager.Calculate(Convert.ToDouble(leftNum), Convert.ToDouble(rightNum), (x, y) => x * y);
                    break;
                case "/":
                    count = CalculateManager.Calculate(Convert.ToDouble(leftNum), Convert.ToDouble(rightNum),(x,y) => x / y);
                    break;

            }

            return count.ToString();
        }


        public virtual List<string> ChangeAsCustomFeatchures(List<string> ExerciseList)
        {

            var newExerciseList = new List<string>();
            for (int i = 0; i < ExerciseList.Count; i++)
            {

                string count;
                if (ExerciseList[i] == "/" || ExerciseList[i] == "*")
                {
                    count = Count(ExerciseList[i - 1], ExerciseList[i], ExerciseList[i + 1]);//Count method in base can count actions with only '+','-','*','/' actions
                    newExerciseList[newExerciseList.Count] = count;

                }
                else
                {
                    newExerciseList.Add(ExerciseList[i]);
                }
            }
            return newExerciseList;
        }
        public List<string> Generator(Func<string> action, Func<string> changeable)
        {
            List<string> list = new List<string>();
            int count = rnd.Next(3, 12); //Random Count - changeable + actions

            for (int i = 1; i <= count; i++)
            {
                if (i % 2 != 0)
                {
                    string number = changeable();
                    list.Add(number);
                }
                else
                {
                    if (i == count)
                        break;
                    string currAction = action();
                    list.Add(currAction);
                }
            }
            return list;
        }
     
        protected string ListToString(List<string> list)
        {
            
            string question = "";

            foreach (var item in list)
            {
                question = question + item;
            }

            return question;
        }
    }
}
