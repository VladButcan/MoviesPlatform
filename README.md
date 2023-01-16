                            POO TV
    Class main declare Json for read input, ObjectMapper, ArrayNode 
and ArrayList back for save access pages and currentPage.
    Interface typeOfActions call function actionsType for return, 
with factory pattern, from string some CurrentPage that is given 
by actions node.
    Current page call function act for do changePage or some actions 
on current page with command pattern.
    After successful change page, last page was saved in back 
history arrayList, same for successful login, register or logout 
after that pages is changed.
    For first currentPage is set to HomePageNeautentificat. 
From folder HomePageNeautentificat user have acces just to login and 
register page.

    After login or register user go to HomePageAutentificat.
In folder homepageautentificat user can acces folder MoviesPage, 
SeeUpgradesPages and SeeDetailsPages that have de same name class 
that implements CurrentPageInterface. For change the page program 
use switch function for return nea currentPage that will call at next 
actions node.
    If user access MoviesPages output ArrayNode is creating with 
builder pattern that use universal arrayNode. If user try to acceess 
another page instead three that is indicate, in output arrayNode 
program add a error node.
    At page HomePageAutentificat user can't do some actions that 
result error node.

    At UpgradesPages program use switch for set BuyAction like 
buy premium or buy token after that is calling with this class that 
implements BuyAction personal function.

    At MoviesPage user see all movies that are allowde in hi's contry.
At this page user have to do search on list and filter list. For 
simplify sorting program have Duration Decreasing / Increasing and 
Rating Decreasing / Increasing class that sort de movie list by 
indicate parameters. This class implement SortType interface.

    User have access to SeeDetailsPages just from MoviesPage where 
he check info about soe movie if this movie exist on site after that
he can purchase, watch, like or rate them.
    If he try to watch without purchase he will take a error. For 
purchase the movie, class PremiumAccountType and StandartAccountType 
implements AccountType interface after that buy movie with free movies
 if he are premium and with 2 tokens if use all 15 free movies,
or with 2 token if he are sandart.

    In time what user check the movie site system have access to 
update database with add movie or delete movie.
    For this actions class AddedMovie check if movie exist in list,
and for false answer he add the movie. Because site wont to notify 
users that implement notify interface about added movie, site check 
what user are subscribed to same genres that have movies and 
use observer pattern for notify them.

    If site decide to delete movie, it delete this movie from all 
users array list with purchase, watched, liked or rated and for user 
that buy the movie site return the money for standart account or 
increasing numFreePremiumMovies for preimum account. User that buyed 
movies will be notify.

    After all actions are finished, if user don't logout and he are
premium, site decide to recommend best movie for him. For this 
site use special algoritms that check what is hi's favorite genres 
and create for this HashMap for save gen and like that user give for 
movies that contain this genre.
    After this site sorting moviesArrayList decreasing by number 
of likes and check if user don't watch the best movie and this movie 
genre that user like. When it find best movie, site notify user about this and set on notifications movie name and message "recomendation".

    For create a jsonOut file Class JsonOut save current user that 
access site and use builder pattern for create node with parametres 
for each pages.
    Same jsonOut have functions for create object node and ArrayNode.


Name:
Vlad Butcan

Faculty:
Faculty of Automatic Control and Computer Science

Year:
II

Group:
325CD

TeamID:
vlad.butcan

Email:
butcanvlad10@gmail.com

