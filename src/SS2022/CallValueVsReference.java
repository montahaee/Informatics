package SS2022;

/**
 * Call by Value means calling a method with a parameter
 * as a value through which the argument value is passed
 * to the parameter.
 * While call by Reference means calling a methode with parameter
 * as a reference through which the argument reference is passed
 * to the parameter.
 * In other words the call by value is a parameter passing methode,
 * in which values of actual parameter are copied to the
 * function's formal parameter or  in the scope. These two types
 * of parameters will be stored in different memory locations.
 * So any changes inside body function or scope are not reflected
 * in the actual parameters of the caller.
 * Unlike to that call by reference the both actual and formal
 * parameters refer to the same memory locations, so any change inside
 * the function body or generally in a scope are actually reflected
 * in the actual parameter of the caller.
 * Notice java uses only call by value while passing reference variables
 * as well. It creates a copy of reference and passes them as valuable to
 * the methods. As reference points to the same address of object,
 * creating a copy of the reference is of no harm. But if new object
 * is assigned to the reference it will not be reflected.
 *
 * <a href="https://en.wikipedia.org/wiki/Call_by_value#Call_by_value"> call by value vs. by reference</a>
 *
 * Garbage collection:
 * assignment statement c1 = c2, c1 points to the
 * same object referenced by c2. The object previously
 * referenced by c1 is no longer useful and therefore is
 * now known as garbage. Garbage occupies memory space,
 * so the Java runtime system detects garbage and
 * automatically reclaims the space it occupies. This
 * process is called garbage collection
 *
 * Für die möglichst umfassende, aber redundanzarme Prüfung der Testverfahren
 * spezifizierten Funktionalität bieten sich funktionale Testverfah-
 * ren (Black-Box-Tests) an, bei denen das zu testende System ein
 * »schwarzer Kasten« ist, d. h. die interne Programmstruktur ist dem
 * Tester nicht bekannt. Ein vollständiger Funktionstest ist i. Allg. nicht
 * durchführbar. Ziel bei der Ermittlung der Abnahmekriterien muss es
 * daher sein, Testfälle so auszuwählen, dass die Wahrscheinlichkeit
 * groß ist, Fehler zu finden. Für die Testfallbestimmung gibt es folgende
 * wichtige Verfahren
 * In der Softwareentwicklung ist es besonderes wichtig, einen festen
 * , definierte status der Software zu kennen. so z.B. ist die jetzige,
 * neue Softwareversion besser als die alte Version? Die Automatischen
 * Tests, die nach dem Einspielen einer Änderung unerwünschte Auswirkungen
 * auf andere Funktion abtest, werden Reressionstest genannt.
 */

public class CallValueVsReference {

}
