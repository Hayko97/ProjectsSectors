using ExerciseGame.Core.Randoms;
using ExerciseGame.Data;


using ExerciseGame.Data.Interfaces;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExerciseGame.Core
{
    public sealed class SimpleExercise : Exercise, IExercise
    {
  
        private Random rnd = new Random();
        public SimpleExercise()
        {
            ExerciseList = Generator(Action,Changeable);
            ExerciseQuestion = ListToString(ExerciseList);
        }

        public override string Changeable()
        {
            int Num = rnd.Next(1, 200);

            return Num.ToString();

        }


        public bool CheckAnswer(string Answer)
        {
            ExerciseList = ChangeAsCustomFeatchures(ExerciseList);
            string ourAnswer;
            for (int i = 1; i <= ExerciseList.Count; i++)
            {
                if (i % 2 == 0)
                {
                    ExerciseList[i + 1] = Count(ExerciseList[i - 1], ExerciseList[i], ExerciseList[i + 1]);
                }
            }
            ourAnswer = ExerciseList[ExerciseList.Count];
            if (ourAnswer == Answer)
                return true;
            else
                return false;
        }
        
    }
}
