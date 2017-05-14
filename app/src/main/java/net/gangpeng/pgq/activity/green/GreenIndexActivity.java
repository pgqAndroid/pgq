package net.gangpeng.pgq.activity.green;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.L;
import com.gangpeng.pgframe.util.T;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.adapter.UserAdapter;
import net.gangpeng.pgq.application.PgqApplication;
import net.gangpeng.pgq.domain.User;
import net.gangpeng.pgq.greendao.DaoMaster;
import net.gangpeng.pgq.greendao.DaoSession;
import net.gangpeng.pgq.greendao.UserDao;
import net.gangpeng.pgq.util.SysCode;
import net.gangpeng.pgq.view.TextEditLayout;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.green.GreenIndexActivity
 * function: greenDao目录
 * <p/>
 * created at 17/1/9 16:03
 *
 * @author gangpeng
 */
public class GreenIndexActivity extends BaseActivity {

    @InjectView(R.id.te_name)
    TextEditLayout teName;
    @InjectView(R.id.te_age)
    TextEditLayout teAge;
    @InjectView(R.id.btn_query)
    Button btnQuery;
    @InjectView(R.id.btn_query_cdt)
    Button btnQueryCdt;
    @InjectView(R.id.recyclerViewNotes)
    RecyclerView recyclerViewNotes;

    private UserDao noteDao;
    private Query<User> notesQuery;
    private UserAdapter notesAdapter;

    private PgqApplication pgqApplication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);
        ButterKnife.inject(this);

        setUpViews();

        pgqApplication = (PgqApplication) getApplication();
        Database db = pgqApplication.getDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        noteDao = daoSession.getUserDao();

        // query all notes, sorted a-z by their text
        notesQuery = noteDao.queryBuilder().orderAsc(UserDao.Properties.Name).build();
        updateNotes();
    }

    private void updateNotes() {
        List<User> notes = notesQuery.list();
        notesAdapter.setNotes(notes);
    }

    private void queryUserByName(String key) {
        List<User> notes = noteDao.queryBuilder().where(UserDao.Properties.Name.like(key)).list();
        notesAdapter.setNotes(notes);
    }

    protected void setUpViews() {
        //noinspection ConstantConditions
        recyclerViewNotes.setHasFixedSize(true);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

        notesAdapter = new UserAdapter(noteClickListener);
        recyclerViewNotes.setAdapter(notesAdapter);
    }

    private void addNote() {
        String name = teName.getEditText();
        teName.setEditText("");
        String ageStr = teAge.getEditText();
        teAge.setEditText("");

        int age = 0;
        try {
            age = Integer.parseInt(ageStr);
        } catch (Exception e) {
            age = 0;
        }

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        User note = new User(null, age, 0, name, comment, "");
        noteDao.insert(note);
        L.d("Inserted new note, ID: " + note.getId());

        updateNotes();
    }

    UserAdapter.NoteClickListener noteClickListener = new UserAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            User note = notesAdapter.getNote(position);
            Long noteId = note.getId();
            noteDao.deleteByKey(noteId);
            L.d("Deleted note, ID: " + noteId);
            updateNotes();
        }
    };

    @OnClick({R.id.btn_query, R.id.btn_query_cdt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_query:
                if (teName.getEditText().length() == 0) {
                    T.showToastNotRepeat(this, "请输入姓名", SysCode.TOAST_TIME);
                    return;
                }
                addNote();
                break;
            case R.id.btn_query_cdt:
                queryUserByName("%a%");
                break;
        }
    }
}
