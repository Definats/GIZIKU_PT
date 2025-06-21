package com.example.giziku.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.giziku.model.AnakEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AnakDao_Impl implements AnakDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AnakEntity> __insertionAdapterOfAnakEntity;

  private final EntityDeletionOrUpdateAdapter<AnakEntity> __deletionAdapterOfAnakEntity;

  private final EntityDeletionOrUpdateAdapter<AnakEntity> __updateAdapterOfAnakEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAnakById;

  public AnakDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAnakEntity = new EntityInsertionAdapter<AnakEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `anak` (`id`,`nama`,`tanggalLahir`,`jenisKelamin`,`beratBadan`,`tinggiBadan`,`kodeUnik`,`orangTuaId`,`kelas`,`fileUri`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AnakEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNama());
        statement.bindString(3, entity.getTanggalLahir());
        statement.bindString(4, entity.getJenisKelamin());
        statement.bindString(5, entity.getBeratBadan());
        statement.bindString(6, entity.getTinggiBadan());
        statement.bindString(7, entity.getKodeUnik());
        statement.bindLong(8, entity.getOrangTuaId());
        if (entity.getKelas() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getKelas());
        }
        statement.bindString(10, entity.getFileUri());
      }
    };
    this.__deletionAdapterOfAnakEntity = new EntityDeletionOrUpdateAdapter<AnakEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `anak` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AnakEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAnakEntity = new EntityDeletionOrUpdateAdapter<AnakEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `anak` SET `id` = ?,`nama` = ?,`tanggalLahir` = ?,`jenisKelamin` = ?,`beratBadan` = ?,`tinggiBadan` = ?,`kodeUnik` = ?,`orangTuaId` = ?,`kelas` = ?,`fileUri` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AnakEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNama());
        statement.bindString(3, entity.getTanggalLahir());
        statement.bindString(4, entity.getJenisKelamin());
        statement.bindString(5, entity.getBeratBadan());
        statement.bindString(6, entity.getTinggiBadan());
        statement.bindString(7, entity.getKodeUnik());
        statement.bindLong(8, entity.getOrangTuaId());
        if (entity.getKelas() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getKelas());
        }
        statement.bindString(10, entity.getFileUri());
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAnakById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM anak WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAnak(final AnakEntity anak, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAnakEntity.insert(anak);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAnakKeKelas(final AnakEntity anak,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAnakEntity.insert(anak);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAnak(final AnakEntity anak, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAnakEntity.handle(anak);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAnak(final AnakEntity anak, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAnakEntity.handle(anak);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAnakById(final int anakId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAnakById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, anakId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAnakById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AnakEntity>> getAnakByOrangTuaId(final long orangTuaId) {
    final String _sql = "SELECT * FROM anak WHERE orangTuaId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orangTuaId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"anak"}, new Callable<List<AnakEntity>>() {
      @Override
      @NonNull
      public List<AnakEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
          final int _cursorIndexOfTanggalLahir = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggalLahir");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfBeratBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "beratBadan");
          final int _cursorIndexOfTinggiBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggiBadan");
          final int _cursorIndexOfKodeUnik = CursorUtil.getColumnIndexOrThrow(_cursor, "kodeUnik");
          final int _cursorIndexOfOrangTuaId = CursorUtil.getColumnIndexOrThrow(_cursor, "orangTuaId");
          final int _cursorIndexOfKelas = CursorUtil.getColumnIndexOrThrow(_cursor, "kelas");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final List<AnakEntity> _result = new ArrayList<AnakEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnakEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpTanggalLahir;
            _tmpTanggalLahir = _cursor.getString(_cursorIndexOfTanggalLahir);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpBeratBadan;
            _tmpBeratBadan = _cursor.getString(_cursorIndexOfBeratBadan);
            final String _tmpTinggiBadan;
            _tmpTinggiBadan = _cursor.getString(_cursorIndexOfTinggiBadan);
            final String _tmpKodeUnik;
            _tmpKodeUnik = _cursor.getString(_cursorIndexOfKodeUnik);
            final long _tmpOrangTuaId;
            _tmpOrangTuaId = _cursor.getLong(_cursorIndexOfOrangTuaId);
            final String _tmpKelas;
            if (_cursor.isNull(_cursorIndexOfKelas)) {
              _tmpKelas = null;
            } else {
              _tmpKelas = _cursor.getString(_cursorIndexOfKelas);
            }
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _item = new AnakEntity(_tmpId,_tmpNama,_tmpTanggalLahir,_tmpJenisKelamin,_tmpBeratBadan,_tmpTinggiBadan,_tmpKodeUnik,_tmpOrangTuaId,_tmpKelas,_tmpFileUri);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAnakById(final int id, final Continuation<? super AnakEntity> $completion) {
    final String _sql = "SELECT * FROM anak WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AnakEntity>() {
      @Override
      @Nullable
      public AnakEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
          final int _cursorIndexOfTanggalLahir = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggalLahir");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfBeratBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "beratBadan");
          final int _cursorIndexOfTinggiBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggiBadan");
          final int _cursorIndexOfKodeUnik = CursorUtil.getColumnIndexOrThrow(_cursor, "kodeUnik");
          final int _cursorIndexOfOrangTuaId = CursorUtil.getColumnIndexOrThrow(_cursor, "orangTuaId");
          final int _cursorIndexOfKelas = CursorUtil.getColumnIndexOrThrow(_cursor, "kelas");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final AnakEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpTanggalLahir;
            _tmpTanggalLahir = _cursor.getString(_cursorIndexOfTanggalLahir);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpBeratBadan;
            _tmpBeratBadan = _cursor.getString(_cursorIndexOfBeratBadan);
            final String _tmpTinggiBadan;
            _tmpTinggiBadan = _cursor.getString(_cursorIndexOfTinggiBadan);
            final String _tmpKodeUnik;
            _tmpKodeUnik = _cursor.getString(_cursorIndexOfKodeUnik);
            final long _tmpOrangTuaId;
            _tmpOrangTuaId = _cursor.getLong(_cursorIndexOfOrangTuaId);
            final String _tmpKelas;
            if (_cursor.isNull(_cursorIndexOfKelas)) {
              _tmpKelas = null;
            } else {
              _tmpKelas = _cursor.getString(_cursorIndexOfKelas);
            }
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _result = new AnakEntity(_tmpId,_tmpNama,_tmpTanggalLahir,_tmpJenisKelamin,_tmpBeratBadan,_tmpTinggiBadan,_tmpKodeUnik,_tmpOrangTuaId,_tmpKelas,_tmpFileUri);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAnakById(final String id, final Continuation<? super AnakEntity> $completion) {
    final String _sql = "SELECT * FROM anak WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AnakEntity>() {
      @Override
      @Nullable
      public AnakEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
          final int _cursorIndexOfTanggalLahir = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggalLahir");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfBeratBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "beratBadan");
          final int _cursorIndexOfTinggiBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggiBadan");
          final int _cursorIndexOfKodeUnik = CursorUtil.getColumnIndexOrThrow(_cursor, "kodeUnik");
          final int _cursorIndexOfOrangTuaId = CursorUtil.getColumnIndexOrThrow(_cursor, "orangTuaId");
          final int _cursorIndexOfKelas = CursorUtil.getColumnIndexOrThrow(_cursor, "kelas");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final AnakEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpTanggalLahir;
            _tmpTanggalLahir = _cursor.getString(_cursorIndexOfTanggalLahir);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpBeratBadan;
            _tmpBeratBadan = _cursor.getString(_cursorIndexOfBeratBadan);
            final String _tmpTinggiBadan;
            _tmpTinggiBadan = _cursor.getString(_cursorIndexOfTinggiBadan);
            final String _tmpKodeUnik;
            _tmpKodeUnik = _cursor.getString(_cursorIndexOfKodeUnik);
            final long _tmpOrangTuaId;
            _tmpOrangTuaId = _cursor.getLong(_cursorIndexOfOrangTuaId);
            final String _tmpKelas;
            if (_cursor.isNull(_cursorIndexOfKelas)) {
              _tmpKelas = null;
            } else {
              _tmpKelas = _cursor.getString(_cursorIndexOfKelas);
            }
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _result = new AnakEntity(_tmpId,_tmpNama,_tmpTanggalLahir,_tmpJenisKelamin,_tmpBeratBadan,_tmpTinggiBadan,_tmpKodeUnik,_tmpOrangTuaId,_tmpKelas,_tmpFileUri);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAnakByKodeUnik(final String kode,
      final Continuation<? super AnakEntity> $completion) {
    final String _sql = "SELECT * FROM anak WHERE kodeUnik = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, kode);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AnakEntity>() {
      @Override
      @Nullable
      public AnakEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
          final int _cursorIndexOfTanggalLahir = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggalLahir");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfBeratBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "beratBadan");
          final int _cursorIndexOfTinggiBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggiBadan");
          final int _cursorIndexOfKodeUnik = CursorUtil.getColumnIndexOrThrow(_cursor, "kodeUnik");
          final int _cursorIndexOfOrangTuaId = CursorUtil.getColumnIndexOrThrow(_cursor, "orangTuaId");
          final int _cursorIndexOfKelas = CursorUtil.getColumnIndexOrThrow(_cursor, "kelas");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final AnakEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpTanggalLahir;
            _tmpTanggalLahir = _cursor.getString(_cursorIndexOfTanggalLahir);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpBeratBadan;
            _tmpBeratBadan = _cursor.getString(_cursorIndexOfBeratBadan);
            final String _tmpTinggiBadan;
            _tmpTinggiBadan = _cursor.getString(_cursorIndexOfTinggiBadan);
            final String _tmpKodeUnik;
            _tmpKodeUnik = _cursor.getString(_cursorIndexOfKodeUnik);
            final long _tmpOrangTuaId;
            _tmpOrangTuaId = _cursor.getLong(_cursorIndexOfOrangTuaId);
            final String _tmpKelas;
            if (_cursor.isNull(_cursorIndexOfKelas)) {
              _tmpKelas = null;
            } else {
              _tmpKelas = _cursor.getString(_cursorIndexOfKelas);
            }
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _result = new AnakEntity(_tmpId,_tmpNama,_tmpTanggalLahir,_tmpJenisKelamin,_tmpBeratBadan,_tmpTinggiBadan,_tmpKodeUnik,_tmpOrangTuaId,_tmpKelas,_tmpFileUri);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAnakByKelas(final String kelas,
      final Continuation<? super List<AnakEntity>> $completion) {
    final String _sql = "SELECT * FROM anak WHERE kelas = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, kelas);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnakEntity>>() {
      @Override
      @NonNull
      public List<AnakEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
          final int _cursorIndexOfTanggalLahir = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggalLahir");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfBeratBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "beratBadan");
          final int _cursorIndexOfTinggiBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggiBadan");
          final int _cursorIndexOfKodeUnik = CursorUtil.getColumnIndexOrThrow(_cursor, "kodeUnik");
          final int _cursorIndexOfOrangTuaId = CursorUtil.getColumnIndexOrThrow(_cursor, "orangTuaId");
          final int _cursorIndexOfKelas = CursorUtil.getColumnIndexOrThrow(_cursor, "kelas");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final List<AnakEntity> _result = new ArrayList<AnakEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnakEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpTanggalLahir;
            _tmpTanggalLahir = _cursor.getString(_cursorIndexOfTanggalLahir);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpBeratBadan;
            _tmpBeratBadan = _cursor.getString(_cursorIndexOfBeratBadan);
            final String _tmpTinggiBadan;
            _tmpTinggiBadan = _cursor.getString(_cursorIndexOfTinggiBadan);
            final String _tmpKodeUnik;
            _tmpKodeUnik = _cursor.getString(_cursorIndexOfKodeUnik);
            final long _tmpOrangTuaId;
            _tmpOrangTuaId = _cursor.getLong(_cursorIndexOfOrangTuaId);
            final String _tmpKelas;
            if (_cursor.isNull(_cursorIndexOfKelas)) {
              _tmpKelas = null;
            } else {
              _tmpKelas = _cursor.getString(_cursorIndexOfKelas);
            }
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _item = new AnakEntity(_tmpId,_tmpNama,_tmpTanggalLahir,_tmpJenisKelamin,_tmpBeratBadan,_tmpTinggiBadan,_tmpKodeUnik,_tmpOrangTuaId,_tmpKelas,_tmpFileUri);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAnakByUserId(final long userId,
      final Continuation<? super AnakEntity> $completion) {
    final String _sql = "SELECT * FROM anak WHERE orangTuaId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AnakEntity>() {
      @Override
      @Nullable
      public AnakEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
          final int _cursorIndexOfTanggalLahir = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggalLahir");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfBeratBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "beratBadan");
          final int _cursorIndexOfTinggiBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggiBadan");
          final int _cursorIndexOfKodeUnik = CursorUtil.getColumnIndexOrThrow(_cursor, "kodeUnik");
          final int _cursorIndexOfOrangTuaId = CursorUtil.getColumnIndexOrThrow(_cursor, "orangTuaId");
          final int _cursorIndexOfKelas = CursorUtil.getColumnIndexOrThrow(_cursor, "kelas");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final AnakEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpTanggalLahir;
            _tmpTanggalLahir = _cursor.getString(_cursorIndexOfTanggalLahir);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpBeratBadan;
            _tmpBeratBadan = _cursor.getString(_cursorIndexOfBeratBadan);
            final String _tmpTinggiBadan;
            _tmpTinggiBadan = _cursor.getString(_cursorIndexOfTinggiBadan);
            final String _tmpKodeUnik;
            _tmpKodeUnik = _cursor.getString(_cursorIndexOfKodeUnik);
            final long _tmpOrangTuaId;
            _tmpOrangTuaId = _cursor.getLong(_cursorIndexOfOrangTuaId);
            final String _tmpKelas;
            if (_cursor.isNull(_cursorIndexOfKelas)) {
              _tmpKelas = null;
            } else {
              _tmpKelas = _cursor.getString(_cursorIndexOfKelas);
            }
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _result = new AnakEntity(_tmpId,_tmpNama,_tmpTanggalLahir,_tmpJenisKelamin,_tmpBeratBadan,_tmpTinggiBadan,_tmpKodeUnik,_tmpOrangTuaId,_tmpKelas,_tmpFileUri);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
