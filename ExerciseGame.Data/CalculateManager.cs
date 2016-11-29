using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExerciseGame.Data
{
    static class CalculateManager
    {
        public static double Calculate(double left, double right, Func<double, double, double> calc)
        {
            return calc(left, right);
        }

   
    }
}
