#include <iostream>
using namespace std;
/**
 * In Call by value, the evaluated value of the argument
 * (a value used to evaluate a procedure/ independent variable
 * of thee function) expression is bound to the corresponding
 * variable in the function(frequently by copying the value into
 * a new memory region). If the function or procedure is able to
 * assign values to its parameters, only its local variables is assigned
 * that is, anything passed into a function call is unchanged in the
 * caller scope when the function returns.
 * Call by reference (or pass by reference ) is an evaluation strategy
 * where a parameter is bound to implicit reference to the variable
 * used as argument, rather than a copy of its value.
 *
 * @return
 */

void modify(int p, int* q, int* r);
int main() {

    int a = 1;
    int b = 1;
    int x = 1;
    int* c = & x;
    cout << "c: " << c << endl;
    modify(a, &b, c); // (D)
    cout << "a: " << a << ", b: " << b << ", c: " << c << ", x: "<<  x << endl;
    return 0;
}

void modify(int p, int* q, int* r) {
    p = 27;                         // (A)
    *q = 27;                       //  (B)
    *r = 27;                      //   (C)
}
// (A): passed by value only the local parameter is modified
// (B): passed by value or reference
// (C): passed by value or reference
// (D):'a' is passed by value ; 'b' is passed by reference
// by creating a pointer(call by value), 'c' is a pointer
// passed by value 'b' and 'x' are changed.
