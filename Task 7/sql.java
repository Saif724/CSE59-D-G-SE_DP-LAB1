

class SqlQuery {
    private final String query;

    private SqlQuery(Builder builder){
        this.query= "SELECT "+builder.select + " FROM "  +builder.from + (builder.where!=null ? " WHERE " + builder.where : ";");
    }

    public String getQuery(){
        return query;
    }

    public static class Builder {

        private String select="*";
        private String from;
        private String where;

        public Builder from(String from){
            this.from=from;
            return this;
        }
        public Builder select(String select){
            this.select=select;
            return this;
        }
        public Builder where(String where){
            this.where=where;
            return this;
        }

        public SqlQuery build(){
            if(this.from == null)throw new IllegalStateException("Error: FROM required");
            return new SqlQuery(this);
        }
    }
}

public class sql {
    public static void main(String[] args){
        SqlQuery sq=new SqlQuery.Builder()
                    .select("name, registration_num")
                    .from("students")
                    .where("batch >= 20")
                    .build();

        System.out.println(sq.getQuery());
    }
}
