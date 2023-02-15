package mk.finki.ukim.mealsmack.pipeandfilter;

public class RawDataFilter implements Filter<String>{
    @Override
    public String execute(String input) {
        String []parts = input.split("\\,",-1);

        if(!(parts[1].equals("")) && !(parts[2].equals("")) && !(parts[4].equals("")) ){
            String s = "";

            for(int i = 0;i<7;i++){
                s+=parts[i]+",";
            }
            s+="\n";

            return s.substring(0,s.length()-2);
        }
        return " ";
    }
}
