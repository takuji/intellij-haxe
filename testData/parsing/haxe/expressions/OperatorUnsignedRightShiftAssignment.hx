class Test {
  static public function main() {
    var c : Int = -1;
    c >>>= 2;       // Now 0x3FFFFFFF
    trace (c);
  }
}