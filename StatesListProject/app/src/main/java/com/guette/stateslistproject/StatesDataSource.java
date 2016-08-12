package com.guette.stateslistproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAGUETTE on 02/08/2016.
 */
public class StatesDataSource{
    SQLiteDatabase sqLiteDatabase;
    StatesDatabaseHelper statesDatabaseHelper;
    String[] all_states={StatesDatabaseHelper.IDSTATE, StatesDatabaseHelper.NAMESTATE, StatesDatabaseHelper.CAPITALSTATE,
            StatesDatabaseHelper.LATITUDE, StatesDatabaseHelper.LONGITUDE};
    private Context context;
    private State state;
    public StatesDataSource(Context context) {
        this.context=context;
        statesDatabaseHelper = new StatesDatabaseHelper(context);
        try {
            open();
        }catch (SQLException e){
            Log.e("Connection ", "Openning connection " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() {
        sqLiteDatabase=statesDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        statesDatabaseHelper.close();
    }

    public State createState(String nameState, String capitalState, String latitude, String longitude){
        ContentValues values= new ContentValues();
        values.put(StatesDatabaseHelper.NAMESTATE, nameState);
        values.put(StatesDatabaseHelper.CAPITALSTATE, capitalState);
        values.put(StatesDatabaseHelper.LATITUDE, latitude);
        values.put(StatesDatabaseHelper.LONGITUDE, longitude);
        Long idInsert= sqLiteDatabase.insert(StatesDatabaseHelper.TABLENAME, null, values);
        Cursor cursor=sqLiteDatabase.query(StatesDatabaseHelper.TABLENAME, all_states, StatesDatabaseHelper.IDSTATE + " = "+
                idInsert,null,null,null,null);
        cursor.moveToFirst();
        State newState= cursorToState(cursor);
        cursor.close();
        return newState ;
    }

    public List<State> getAllStates() {
        List<State> listStates= new ArrayList<State>();
        Cursor cursor= sqLiteDatabase.query(StatesDatabaseHelper.TABLENAME,all_states,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                State state=cursorToState(cursor);
                listStates.add(state);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listStates;
    }

    public State getStateById(int idState){
        Log.d("getStateById ", ""+idState);
        Cursor cursor=sqLiteDatabase.query(StatesDatabaseHelper.TABLENAME, all_states, StatesDatabaseHelper.IDSTATE +" = ?",
		new String[] { String.valueOf(idState) },null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        State state = cursorToState(cursor);
        return state;
    }

    public State cursorToState(Cursor cursor) {
        State state= new State();
        state.setIdStates(cursor.getLong(0));
        state.setNameStates(cursor.getString(1));
        state.setCapitalStates(cursor.getString(2));
        state.setLatitude(cursor.getString(3));
        state.setLongitude(cursor.getString(4));
        return state;
    }

}
