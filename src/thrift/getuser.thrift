namespace java thrift

typedef i32 int
typedef string String

struct Student {
    1: String name,
    2: int age,
    3: required String email,
    4: optional String datetime
}

service UserService{
    String getUserById(1:int id, 2: optinal String time);
    String getUser2(1:int id, 2: String time);
    Student getFullInfo(1:int id);
}
