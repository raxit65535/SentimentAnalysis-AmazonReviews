awk -F'::' 'NF == 4 { sum[$2] += $3; N[$2]++ } 
          END     { for (key in sum) {
                        avg = sum[key] / N[key];
                        printf "%s %f\n", key, avg;
                    } }' ratings.dat|sort -rk 2|head -25
