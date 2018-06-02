package DAL;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Productos(IdProducto integer primary key,NombreProducto text,TipoMedida text, IdCategoria integer,IdProveedor integer,UrlImagen text, Descripcion text, TipoProducto text,Marca text,Precio real, Cantidad integer,FechaVenc text,CreatedOn text,ModifiedOn text,DeletedOn text,Inactivo integer,bitmaps blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Productos");
        sqLiteDatabase.execSQL("create table Productos(IdProducto integer primary key,NombreProducto text,TipoMedida text, IdCategoria integer,IdProveedor integer,UrlImagen text, Descripcion text, TipoProducto text,Marca text,Precio real, Cantidad integer,FechaVenc text,CreatedOn text,ModifiedOn text,DeletedOn text,Inactivo integer,bitmaps blob)");

    }
}
