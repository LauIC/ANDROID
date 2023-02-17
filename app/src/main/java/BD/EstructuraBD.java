package BD;

public class EstructuraBD {

    private EstructuraBD(){}
        //TABLA USUARIOS
        //constantes tabla USUARIOS
        public static final String NOMBRE_TABLA = "USUARIOS";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE= "nombre_usuario ";
        public static final String COLUMNA_EMAIL = "email";
        public static final String COLUMNA_PASSWORD = "password";
        public static final String COLUMNA_FECHA_US = "fecha_alta";

        //Creamos tabla
        public static final String SQL_CREATE_ENTRIES=
                "CREATE TABLE IF EXISTS " + EstructuraBD.NOMBRE_TABLA +"(" +
                        EstructuraBD.COLUMNA_ID + " integer primary key autoincrement," +
                        EstructuraBD.COLUMNA_NOMBRE + " text not null," +
                        EstructuraBD.COLUMNA_EMAIL + " text not null unique, " +
                        EstructuraBD.COLUMNA_PASSWORD + " text not null, " +
                        EstructuraBD.COLUMNA_FECHA_US + " datetime default CURRENT_TIMESTAMP)";

        //Eliminar tabla
        public static final  String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + EstructuraBD.NOMBRE_TABLA;



    //TABLA DATS FISICOS
    //constantes tabla DATOS FISICOS
    public static final String NOMBRE_TABLA_DF = "DATOS_FISICOS_USUARIOS";
    public static final String COLUMNA_ID_DF = "id";
    public static final String COLUMNA_SEXO_DF= "sexo ";
    public static final String COLUMNA_FEC_NAC_DF = "fecha_nacimiento";
    public static final String COLUMNA_PESO_DF = "peso";
    public static final String COLUMNA_ALTURA_DF = "altura";
    public  static final String COLUMNA_ID_USUARIO_DF = "id_usuario";
    public static final String COLUMNA_FECHA_DF = "fecha_alta";

    //Creamos tabla
    public static final String SQL_CREATE_ENTRIES_DF=
            "CREATE TABLE IF EXISTS " + EstructuraBD.NOMBRE_TABLA_DF +"(" +
                    EstructuraBD.COLUMNA_ID_DF + " integer primary key autoincrement," +
                    EstructuraBD.COLUMNA_SEXO_DF + " char not null," +
                    EstructuraBD.COLUMNA_FEC_NAC_DF + " date not null, " +
                    EstructuraBD.COLUMNA_PESO_DF + " float not null, " +
                    EstructuraBD.COLUMNA_ALTURA_DF + " float not null, " +
                    EstructuraBD.COLUMNA_FECHA_DF + " datetime default CURRENT_TIMESTAMP, " +
                    EstructuraBD.COLUMNA_ID_USUARIO_DF + " integer not null, " +
                    "FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id))";

    //Eliminar tabla
    public static final  String SQL_DELETE_ENTRIES_DF =
            "DROP TABLE IF EXISTS " + EstructuraBD.NOMBRE_TABLA_DF;

}
