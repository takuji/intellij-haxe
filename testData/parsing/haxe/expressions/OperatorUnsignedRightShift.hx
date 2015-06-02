class Test {
  static public function main() {
    var c : Int = -1;
    c = c >>> 1;    // Now 0x7FFFFFFF
    trace (c);
  }
}