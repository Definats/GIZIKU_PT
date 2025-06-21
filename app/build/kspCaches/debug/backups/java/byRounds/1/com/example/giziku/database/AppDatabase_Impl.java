package com.example.giziku.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile ProfileOrangTuaDao _profileOrangTuaDao;

  private volatile ProfileTenagaPendidikanDao _profileTenagaPendidikanDao;

  private volatile ProfileTenagaMedisDao _profileTenagaMedisDao;

  private volatile AnakDao _anakDao;

  private volatile EdukasiDao _edukasiDao;

  private volatile NutrisiDao _nutrisiDao;

  private volatile GrowthDao _growthDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(32) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `user` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `password` TEXT NOT NULL, `role` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `profileOrangTua` (`userOrangTuaId` INTEGER NOT NULL, `username` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `tanggalLahir` TEXT NOT NULL, `jenisKelamin` TEXT NOT NULL, `fileUri` TEXT NOT NULL, PRIMARY KEY(`userOrangTuaId`), FOREIGN KEY(`userOrangTuaId`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_profileOrangTua_userOrangTuaId` ON `profileOrangTua` (`userOrangTuaId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `profileTenagaPendidikan` (`userTenagaPendidikanId` INTEGER NOT NULL, `username` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `mataPelajaran` TEXT NOT NULL, `jenisKelamin` TEXT NOT NULL, `fileUri` TEXT NOT NULL, PRIMARY KEY(`userTenagaPendidikanId`), FOREIGN KEY(`userTenagaPendidikanId`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_profileTenagaPendidikan_userTenagaPendidikanId` ON `profileTenagaPendidikan` (`userTenagaPendidikanId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `profileTenagaMedis` (`userTenagaMedisId` INTEGER NOT NULL, `username` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `spesialis` TEXT NOT NULL, `jenisKelamin` TEXT NOT NULL, `fileUri` TEXT NOT NULL, PRIMARY KEY(`userTenagaMedisId`), FOREIGN KEY(`userTenagaMedisId`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_profileTenagaMedis_userTenagaMedisId` ON `profileTenagaMedis` (`userTenagaMedisId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `anak` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nama` TEXT NOT NULL, `tanggalLahir` TEXT NOT NULL, `jenisKelamin` TEXT NOT NULL, `beratBadan` TEXT NOT NULL, `tinggiBadan` TEXT NOT NULL, `kodeUnik` TEXT NOT NULL, `orangTuaId` INTEGER NOT NULL, `kelas` TEXT, `fileUri` TEXT NOT NULL, FOREIGN KEY(`orangTuaId`) REFERENCES `profileOrangTua`(`userOrangTuaId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `edukasi` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `judul` TEXT NOT NULL, `deskripsi` TEXT NOT NULL, `fileUri` TEXT NOT NULL, FOREIGN KEY(`userId`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_edukasi_userId` ON `edukasi` (`userId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `nutrisi` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `kalori` INTEGER NOT NULL, `protein` INTEGER NOT NULL, `karbohidrat` INTEGER NOT NULL, `lemak` INTEGER NOT NULL, `serat` INTEGER NOT NULL, `gula` INTEGER NOT NULL, `natrium` INTEGER NOT NULL, `kolesterol` INTEGER NOT NULL, `vitamin` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`userId`) REFERENCES `nutrisi`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_nutrisi_userId` ON `nutrisi` (`userId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `growth` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `anakId` INTEGER NOT NULL, `tanggal` INTEGER NOT NULL, `tinggiBadan` TEXT NOT NULL, `beratBadan` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '23c5f21a525ad78d063590a9e0a8c8c8')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `user`");
        db.execSQL("DROP TABLE IF EXISTS `profileOrangTua`");
        db.execSQL("DROP TABLE IF EXISTS `profileTenagaPendidikan`");
        db.execSQL("DROP TABLE IF EXISTS `profileTenagaMedis`");
        db.execSQL("DROP TABLE IF EXISTS `anak`");
        db.execSQL("DROP TABLE IF EXISTS `edukasi`");
        db.execSQL("DROP TABLE IF EXISTS `nutrisi`");
        db.execSQL("DROP TABLE IF EXISTS `growth`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(6);
        _columnsUser.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("user", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(db, "user");
        if (!_infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "user(com.example.giziku.model.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsProfileOrangTua = new HashMap<String, TableInfo.Column>(7);
        _columnsProfileOrangTua.put("userOrangTuaId", new TableInfo.Column("userOrangTuaId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileOrangTua.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileOrangTua.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileOrangTua.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileOrangTua.put("tanggalLahir", new TableInfo.Column("tanggalLahir", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileOrangTua.put("jenisKelamin", new TableInfo.Column("jenisKelamin", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileOrangTua.put("fileUri", new TableInfo.Column("fileUri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProfileOrangTua = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysProfileOrangTua.add(new TableInfo.ForeignKey("user", "CASCADE", "NO ACTION", Arrays.asList("userOrangTuaId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesProfileOrangTua = new HashSet<TableInfo.Index>(1);
        _indicesProfileOrangTua.add(new TableInfo.Index("index_profileOrangTua_userOrangTuaId", false, Arrays.asList("userOrangTuaId"), Arrays.asList("ASC")));
        final TableInfo _infoProfileOrangTua = new TableInfo("profileOrangTua", _columnsProfileOrangTua, _foreignKeysProfileOrangTua, _indicesProfileOrangTua);
        final TableInfo _existingProfileOrangTua = TableInfo.read(db, "profileOrangTua");
        if (!_infoProfileOrangTua.equals(_existingProfileOrangTua)) {
          return new RoomOpenHelper.ValidationResult(false, "profileOrangTua(com.example.giziku.model.ProfileOrangTua).\n"
                  + " Expected:\n" + _infoProfileOrangTua + "\n"
                  + " Found:\n" + _existingProfileOrangTua);
        }
        final HashMap<String, TableInfo.Column> _columnsProfileTenagaPendidikan = new HashMap<String, TableInfo.Column>(7);
        _columnsProfileTenagaPendidikan.put("userTenagaPendidikanId", new TableInfo.Column("userTenagaPendidikanId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaPendidikan.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaPendidikan.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaPendidikan.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaPendidikan.put("mataPelajaran", new TableInfo.Column("mataPelajaran", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaPendidikan.put("jenisKelamin", new TableInfo.Column("jenisKelamin", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaPendidikan.put("fileUri", new TableInfo.Column("fileUri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProfileTenagaPendidikan = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysProfileTenagaPendidikan.add(new TableInfo.ForeignKey("user", "CASCADE", "NO ACTION", Arrays.asList("userTenagaPendidikanId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesProfileTenagaPendidikan = new HashSet<TableInfo.Index>(1);
        _indicesProfileTenagaPendidikan.add(new TableInfo.Index("index_profileTenagaPendidikan_userTenagaPendidikanId", false, Arrays.asList("userTenagaPendidikanId"), Arrays.asList("ASC")));
        final TableInfo _infoProfileTenagaPendidikan = new TableInfo("profileTenagaPendidikan", _columnsProfileTenagaPendidikan, _foreignKeysProfileTenagaPendidikan, _indicesProfileTenagaPendidikan);
        final TableInfo _existingProfileTenagaPendidikan = TableInfo.read(db, "profileTenagaPendidikan");
        if (!_infoProfileTenagaPendidikan.equals(_existingProfileTenagaPendidikan)) {
          return new RoomOpenHelper.ValidationResult(false, "profileTenagaPendidikan(com.example.giziku.model.ProfileTenagaPendidikan).\n"
                  + " Expected:\n" + _infoProfileTenagaPendidikan + "\n"
                  + " Found:\n" + _existingProfileTenagaPendidikan);
        }
        final HashMap<String, TableInfo.Column> _columnsProfileTenagaMedis = new HashMap<String, TableInfo.Column>(7);
        _columnsProfileTenagaMedis.put("userTenagaMedisId", new TableInfo.Column("userTenagaMedisId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaMedis.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaMedis.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaMedis.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaMedis.put("spesialis", new TableInfo.Column("spesialis", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaMedis.put("jenisKelamin", new TableInfo.Column("jenisKelamin", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfileTenagaMedis.put("fileUri", new TableInfo.Column("fileUri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProfileTenagaMedis = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysProfileTenagaMedis.add(new TableInfo.ForeignKey("user", "CASCADE", "NO ACTION", Arrays.asList("userTenagaMedisId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesProfileTenagaMedis = new HashSet<TableInfo.Index>(1);
        _indicesProfileTenagaMedis.add(new TableInfo.Index("index_profileTenagaMedis_userTenagaMedisId", false, Arrays.asList("userTenagaMedisId"), Arrays.asList("ASC")));
        final TableInfo _infoProfileTenagaMedis = new TableInfo("profileTenagaMedis", _columnsProfileTenagaMedis, _foreignKeysProfileTenagaMedis, _indicesProfileTenagaMedis);
        final TableInfo _existingProfileTenagaMedis = TableInfo.read(db, "profileTenagaMedis");
        if (!_infoProfileTenagaMedis.equals(_existingProfileTenagaMedis)) {
          return new RoomOpenHelper.ValidationResult(false, "profileTenagaMedis(com.example.giziku.model.ProfileTenagaMedis).\n"
                  + " Expected:\n" + _infoProfileTenagaMedis + "\n"
                  + " Found:\n" + _existingProfileTenagaMedis);
        }
        final HashMap<String, TableInfo.Column> _columnsAnak = new HashMap<String, TableInfo.Column>(10);
        _columnsAnak.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("nama", new TableInfo.Column("nama", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("tanggalLahir", new TableInfo.Column("tanggalLahir", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("jenisKelamin", new TableInfo.Column("jenisKelamin", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("beratBadan", new TableInfo.Column("beratBadan", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("tinggiBadan", new TableInfo.Column("tinggiBadan", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("kodeUnik", new TableInfo.Column("kodeUnik", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("orangTuaId", new TableInfo.Column("orangTuaId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("kelas", new TableInfo.Column("kelas", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnak.put("fileUri", new TableInfo.Column("fileUri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnak = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAnak.add(new TableInfo.ForeignKey("profileOrangTua", "CASCADE", "NO ACTION", Arrays.asList("orangTuaId"), Arrays.asList("userOrangTuaId")));
        final HashSet<TableInfo.Index> _indicesAnak = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAnak = new TableInfo("anak", _columnsAnak, _foreignKeysAnak, _indicesAnak);
        final TableInfo _existingAnak = TableInfo.read(db, "anak");
        if (!_infoAnak.equals(_existingAnak)) {
          return new RoomOpenHelper.ValidationResult(false, "anak(com.example.giziku.model.AnakEntity).\n"
                  + " Expected:\n" + _infoAnak + "\n"
                  + " Found:\n" + _existingAnak);
        }
        final HashMap<String, TableInfo.Column> _columnsEdukasi = new HashMap<String, TableInfo.Column>(5);
        _columnsEdukasi.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEdukasi.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEdukasi.put("judul", new TableInfo.Column("judul", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEdukasi.put("deskripsi", new TableInfo.Column("deskripsi", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEdukasi.put("fileUri", new TableInfo.Column("fileUri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEdukasi = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysEdukasi.add(new TableInfo.ForeignKey("user", "CASCADE", "NO ACTION", Arrays.asList("userId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesEdukasi = new HashSet<TableInfo.Index>(1);
        _indicesEdukasi.add(new TableInfo.Index("index_edukasi_userId", false, Arrays.asList("userId"), Arrays.asList("ASC")));
        final TableInfo _infoEdukasi = new TableInfo("edukasi", _columnsEdukasi, _foreignKeysEdukasi, _indicesEdukasi);
        final TableInfo _existingEdukasi = TableInfo.read(db, "edukasi");
        if (!_infoEdukasi.equals(_existingEdukasi)) {
          return new RoomOpenHelper.ValidationResult(false, "edukasi(com.example.giziku.model.EdukasiEntity).\n"
                  + " Expected:\n" + _infoEdukasi + "\n"
                  + " Found:\n" + _existingEdukasi);
        }
        final HashMap<String, TableInfo.Column> _columnsNutrisi = new HashMap<String, TableInfo.Column>(12);
        _columnsNutrisi.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("kalori", new TableInfo.Column("kalori", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("protein", new TableInfo.Column("protein", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("karbohidrat", new TableInfo.Column("karbohidrat", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("lemak", new TableInfo.Column("lemak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("serat", new TableInfo.Column("serat", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("gula", new TableInfo.Column("gula", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("natrium", new TableInfo.Column("natrium", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("kolesterol", new TableInfo.Column("kolesterol", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("vitamin", new TableInfo.Column("vitamin", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutrisi.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNutrisi = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysNutrisi.add(new TableInfo.ForeignKey("nutrisi", "CASCADE", "NO ACTION", Arrays.asList("userId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesNutrisi = new HashSet<TableInfo.Index>(1);
        _indicesNutrisi.add(new TableInfo.Index("index_nutrisi_userId", false, Arrays.asList("userId"), Arrays.asList("ASC")));
        final TableInfo _infoNutrisi = new TableInfo("nutrisi", _columnsNutrisi, _foreignKeysNutrisi, _indicesNutrisi);
        final TableInfo _existingNutrisi = TableInfo.read(db, "nutrisi");
        if (!_infoNutrisi.equals(_existingNutrisi)) {
          return new RoomOpenHelper.ValidationResult(false, "nutrisi(com.example.giziku.util.Nutrisi).\n"
                  + " Expected:\n" + _infoNutrisi + "\n"
                  + " Found:\n" + _existingNutrisi);
        }
        final HashMap<String, TableInfo.Column> _columnsGrowth = new HashMap<String, TableInfo.Column>(5);
        _columnsGrowth.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowth.put("anakId", new TableInfo.Column("anakId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowth.put("tanggal", new TableInfo.Column("tanggal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowth.put("tinggiBadan", new TableInfo.Column("tinggiBadan", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowth.put("beratBadan", new TableInfo.Column("beratBadan", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGrowth = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGrowth = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGrowth = new TableInfo("growth", _columnsGrowth, _foreignKeysGrowth, _indicesGrowth);
        final TableInfo _existingGrowth = TableInfo.read(db, "growth");
        if (!_infoGrowth.equals(_existingGrowth)) {
          return new RoomOpenHelper.ValidationResult(false, "growth(com.example.giziku.model.Growth).\n"
                  + " Expected:\n" + _infoGrowth + "\n"
                  + " Found:\n" + _existingGrowth);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "23c5f21a525ad78d063590a9e0a8c8c8", "0e949c34c9d43ec38cabb394f2bc7e8c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user","profileOrangTua","profileTenagaPendidikan","profileTenagaMedis","anak","edukasi","nutrisi","growth");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `user`");
      _db.execSQL("DELETE FROM `profileOrangTua`");
      _db.execSQL("DELETE FROM `profileTenagaPendidikan`");
      _db.execSQL("DELETE FROM `profileTenagaMedis`");
      _db.execSQL("DELETE FROM `anak`");
      _db.execSQL("DELETE FROM `edukasi`");
      _db.execSQL("DELETE FROM `nutrisi`");
      _db.execSQL("DELETE FROM `growth`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProfileOrangTuaDao.class, ProfileOrangTuaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProfileTenagaPendidikanDao.class, ProfileTenagaPendidikanDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProfileTenagaMedisDao.class, ProfileTenagaMedisDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AnakDao.class, AnakDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EdukasiDao.class, EdukasiDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NutrisiDao.class, NutrisiDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GrowthDao.class, GrowthDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public ProfileOrangTuaDao profileOrangTuaDao() {
    if (_profileOrangTuaDao != null) {
      return _profileOrangTuaDao;
    } else {
      synchronized(this) {
        if(_profileOrangTuaDao == null) {
          _profileOrangTuaDao = new ProfileOrangTuaDao_Impl(this);
        }
        return _profileOrangTuaDao;
      }
    }
  }

  @Override
  public ProfileTenagaPendidikanDao profileTenagaPendidikanDao() {
    if (_profileTenagaPendidikanDao != null) {
      return _profileTenagaPendidikanDao;
    } else {
      synchronized(this) {
        if(_profileTenagaPendidikanDao == null) {
          _profileTenagaPendidikanDao = new ProfileTenagaPendidikanDao_Impl(this);
        }
        return _profileTenagaPendidikanDao;
      }
    }
  }

  @Override
  public ProfileTenagaMedisDao profileTenagaMedisDao() {
    if (_profileTenagaMedisDao != null) {
      return _profileTenagaMedisDao;
    } else {
      synchronized(this) {
        if(_profileTenagaMedisDao == null) {
          _profileTenagaMedisDao = new ProfileTenagaMedisDao_Impl(this);
        }
        return _profileTenagaMedisDao;
      }
    }
  }

  @Override
  public AnakDao anakDao() {
    if (_anakDao != null) {
      return _anakDao;
    } else {
      synchronized(this) {
        if(_anakDao == null) {
          _anakDao = new AnakDao_Impl(this);
        }
        return _anakDao;
      }
    }
  }

  @Override
  public EdukasiDao edukasiDao() {
    if (_edukasiDao != null) {
      return _edukasiDao;
    } else {
      synchronized(this) {
        if(_edukasiDao == null) {
          _edukasiDao = new EdukasiDao_Impl(this);
        }
        return _edukasiDao;
      }
    }
  }

  @Override
  public NutrisiDao nutrisiDao() {
    if (_nutrisiDao != null) {
      return _nutrisiDao;
    } else {
      synchronized(this) {
        if(_nutrisiDao == null) {
          _nutrisiDao = new NutrisiDao_Impl(this);
        }
        return _nutrisiDao;
      }
    }
  }

  @Override
  public GrowthDao growthDao() {
    if (_growthDao != null) {
      return _growthDao;
    } else {
      synchronized(this) {
        if(_growthDao == null) {
          _growthDao = new GrowthDao_Impl(this);
        }
        return _growthDao;
      }
    }
  }
}
