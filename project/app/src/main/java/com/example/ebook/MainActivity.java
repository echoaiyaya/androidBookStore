package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Book> bookList = new ArrayList<>();

    ImageView companyBtn;
    ImageView userBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBooks();

        User user;
        user = (User) getApplication();
        if(!user.checkLogin()) {
            initUser();
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bookList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);

        companyBtn = findViewById(R.id.aboutUsBtn);
        companyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutUs =new Intent(MainActivity.this, CompanyActivity.class);
                startActivity(aboutUs);
            }
        });

        userBtn = findViewById(R.id.accountBtn);

        //if user doesn't log in, then go to log in page, else go to user page.
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;
                user = (User) getApplication();
                Log.i("test", String.valueOf(user.checkLogin()));
                if(user.checkLogin()) {
                    Intent userInfo = new Intent(MainActivity.this, UserInfoActivity.class);
                    startActivity(userInfo);
                } else {
                    Intent userInfo = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(userInfo);
                }

            }
        });

        // The adatper that can pass data to detail page
        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                Book mbook = bookList.get(position);
                intent.putExtra("name", mbook.getName());
                intent.putExtra("bookImage", mbook.getImageId());
                intent.putExtra("author", mbook.getAuthor());
                intent.putExtra("desc", mbook.getDescription());
                intent.putExtra("price", mbook.getPrice());
                startActivity(intent);
            }
        });

    }

    // to init a user
    private void initUser() {
        User user;
        user = (User) getApplication();
        user.setFirstName("jinqian");
        user.setLastName("Wang");
        user.setPhone("2265815029");
        user.setAddress("731 William St");
        user.setPwd("123456");
    }


    // to init some book data
    private void initBooks() {
        for (int i = 0; i < 2; i++) {
            Book b1 = new Book("Dungeons & Dragons Monster Manual (Core Rulebook, D&D Roleplaying Game) Hardcover – Illustrated, Sept. 30 2014", R.drawable.book2, "Wizards RPG Team ", "The Monster Manual teaches you how to how to fill your Dungeons & Dragons games with monsters—how to populate the game with pesky goblins and mighty dragons for players to battle or beguile, outwit or outrun.", 45.70);
            Book b2 = new Book("Tales From the Yawning Portal", R.drawable.book3, "Wizards RPG Team", "Within this tome are seven of the most compelling dungeons from the 40+ year history of Dungeons & Dragons. Some are classics that have hosted an untold number of adventurers, while others are some of the most popular adventures ever printed. The seeds of these stories now rest in your hands. D&D's most storied dungeons are now part of your modern repertoire of adventures. Enjoy, and remember to keep a few spare character sheets handy.", 45.23);
            Book b3 = new Book("The Fifth Season Paperback – Aug. 4 2015", R.drawable.book4, "N. K. Jemisin", "\"Intricate and extraordinary.\" - New York Times on The Fifth Season (A New York Times Notable Book of 2015)\n" +
                    "\n" +
                    "The start of a new fantasy trilogy by Hugo, Nebula & World Fantasy Award nominated author N.K. Jemisin.", 16.23);
            Book b4 = new Book("Fantastic Beasts: The Crimes of Grindelwald ― The Original Screenplay", R.drawable.book5, "J. K. Rowling", "J.K. Rowling’s five-film Fantastic Beasts adventure series continues with the original screenplay for Fantastic Beasts: The Crimes of Grindelwald\n" +
                    "At the end of Fantastic Beasts and Where to Find Them, the powerful Dark wizard Gellert Grindelwald was captured in New York with the help of Newt Scamander. But, making good on his threat, Grindelwald escapes custody and sets about gathering followers, most unsuspecting of his true agenda: to raise pure-blood wizards up to rule over all non-magical beings. In an effort to thwart Grindelwald’s plans, Albus Dumbledore enlists Newt, his former Hogwarts student, who agrees to help once again, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world. This second original screenplay from J.K. Rowling, illustrated with stunning line art from MinaLima, expands on earlier events that helped shape the wizarding world, with some surprising nods to the Harry Potter stories that will delight fans of both the books and films.", 20.00);
            Book b5 = new Book("D&D Waterdeep Dragon Heist HC Hardcover – Illustrated, Sept. 18 2018", R.drawable.book6, "Wizards RPG Team", "Experience the ultimate urban treasure hunt in this adventure for the world’s greatest roleplaying game.\n" +
                    " \n" +
                    "“A perfect starter campaign.”—Andrew Whalen, Newsweek\n" +
                    " \n" +
                    "“D&D acolytes are everywhere...Tech workers from Silicon Valley to Brooklyn have long-running campaigns, and the showrunners and the novelist behind ‘Game of Thrones’ have all been Dungeon Masters.”—Neima Jahromi, The New Yorker\n" +
                    " \n" +
                    "“Want to be part of something big?”—A parchment tied to a flying snake, page 38 of Dragon Heist\n" +
                    " \n" +
                    "Welcome to Waterdeep. You’re summoned by Volothamp Geddarm, famous explorer and raconteur, to complete a simple quest. Is anything ever really simple though?\n" +
                    " \n" +
                    "• Waterdeep, known as the City of Splendors, is one of D&D’s most iconic locations. Also the setting for the board game Lords of Waterdeeep, it’s the jewel of the Sword Coast—a sprawling melting pot held together by firm laws and swift justice.\n" +
                    " \n" +
                    "• Take players through levels one to five in this Dungeons & Dragons adventure. Five story arcs guide players through each level for a multi-session campaign experience inspired by classic heist films.\n" +
                    " \n" +
                    "• In D&D, you and your friends coauthor you own legend. Guided by a Dungeon Master, you create characters and play their roles in a story, rolling dice and navigating maps as you unfold a tale as limitless as your imagination.\n" +
                    " \n" +
                    "• Dungeons & Dragons is the world’s greatest roleplaying game. Created in 1974, D&D transformed gaming culture by blending traditional fantasy with miniatures and wargaming.\n" +
                    " \n" +
                    "“[Waterdeep: Dragon Heist] is state of the art tabletop design. . .one of the best introductions to D&D that I’ve ever come across.”—Charlie Hall, Polygon.com\n" +
                    " \n" +
                    "“Waterdeep: Dragon Heist is a shining example of what Dungeons & Dragons can be and should be: fun and unpredictable.”—Gavin Sheehan, BleedingCool.com", 45.70);
            Book b6 = new Book("hello6", R.drawable.book1, "jinqian", "good book", 23.23);
            Book b7 = new Book("hello7", R.drawable.book1, "jinqian", "good book", 23.23);
            Book b8 = new Book("hello8", R.drawable.book1, "jinqian", "good book", 23.23);
            Book b9 = new Book("hello9", R.drawable.book1, "jinqian", "good book", 23.23);
            bookList.add(b1);
            bookList.add(b2);
            bookList.add(b3);
            bookList.add(b4);
            bookList.add(b5);
            bookList.add(b6);
            bookList.add(b7);
            bookList.add(b8);
            bookList.add(b9);

        }
    }
}