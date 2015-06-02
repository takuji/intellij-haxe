package ;
class Base<T> {
  function foo(t:T) { trace(t); }
}

class Mid<T> {
  function bar(t:T) { trace(t); }
}

class Top<T> {
  public function new() {}
  function baz(t:T) { trace(t); }
}

class Main {
  var mida : Mid<Base<String>>=new Mid<Base<String>>();
  var midb : Mid<Base<String>> = new Mid<Base<String>>();
  var midc : Mid< Base< String > > = new Mid< Base< String > > ();
  var midd : Mid < Base < String > >= new Mid < Base < String > >();

  var topa : Top<Mid<Base<String>>>= new Top<Mid<Base<String>>>();
  var topb : Top<Mid<Base<String>>> = new Top<Mid<Base<String>>>();
  var topc : Top< Mid< Base< String > > > = new Top< Mid< Base< String > > > ();
  var topd : Top < Mid < Base < String > > > = new Top < Mid < Base < String > > > ();
}