/*
The device will send your subroutine a datastream buffer (your puzzle input);
your subroutine needs to identify the first position where
the four most recently received characters were all different.
Specifically, it needs to report the number of characters from
the beginning of the buffer to the end of the first such four-character marker.

For example, suppose you receive the following datastream buffer:

mjqjpqmgbljsphdztnvjfqwrcgsmlb
After the first three characters (mjq) have been received,
there haven't been enough characters received yet to find the marker.
The first time a marker could occur is after the fourth character is received,
making the most recent four characters mjqj. Because j is repeated, this isn't a marker.

The first time a marker appears is after the seventh character arrives.
Once it does, the last four characters received are jpqm, which are all different.
In this case, your subroutine should report the value 7,
because the first start-of-packet marker is complete after 7 characters have been processed.

Here are a few more examples:

mjqjpqmgbljsphdztnvjfqwrcgsmlb: first marker after character 7
bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 5
nppdvjthqldpwncqszvftbrmjlhg: first marker after character 6
nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 10
zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 11

--- Part Two ---

A start-of-message marker is just like a start-of-packet marker, except it consists of 14 distinct characters rather than 4.

Here are the first positions of start-of-message markers for all of the above examples:

mjqjpqmgbljsphdztnvjfqwrcgsmlb: first marker after character 19
bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 23
nppdvjthqldpwncqszvftbrmjlhg: first marker after character 23
nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 29
zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 26

How many characters need to be processed before the first start-of-message marker is detected?

input
qhbhzbzzfrzrbzzcjzjrrvcvrvqvvnggnngcgssswbblplrlflfnnnmmjppgddqndnrnlnccpfcfjcjvjdjqqqmhhmwhwmmsnsvsjvjnvjnvjvsjsmjsjccwcqwcqwqjqwjwmwbmmbzbsbvsslbsbbbntnvvphpqqvrrtbrtrfftppbggpzzfhfcfsfmssffmbmzzmqzzblzzzmwwnggjwgjwgjgpgmmjvvmcvmmcfchfhllwmlljqqldqdqttsgsvscsmsnsmstmtssvgsgddwdffbppwfpplhlchhhdvvdrrmttmptmmmjsmshmmmgqmgggzjgzzmwzwcwhchqqfpfvvbqvbqbrblrrmtmstmmjvmmdnmmzczdzpztppjhjjwzjjjtdjjpljpjcppjllsffhbffbhhgttqjqzzfzbzcbzcbcrrjjrwrgwwbcbpcccctrtqtfqqfjjpgpdgdfgfrggpjjljglgclcqcqmcqmmgjjllpmphpjjgfjjqrrbppwmpmccftctjtjgjtgggzffcggwzzzdjdzzlgzgjzzvqvppczzjnjvvfhhtwtttdwtdtvddpzpnpcnppmvmcmcsmstthctchcggtssdttvztvvldlfftqqbzzjttvzztppscctzccgmcmvmhhchcscbbshbssgwwthwhmwwcgwcwrrvrzvrzzzvhvdvmmprrdmrmfmrmbmjbbmqbmbqqhbbszsjjlqjljtjstshhgphpffdhhtggtgbbqcqgqccfffcpcbpbfppwqpqcclbbwdwsscpchhfpfmpfmflfnnmggwrrznnghgvhhghrrhwrrcschcscqcmcfcvvgzztjtqjjshspsqsmmjnnmttsshvhmmqfqzztbzttvhttmwttnqnfncfcpfflmllmtlmmphmhlmmltmtztcczhzbbfmmlglnnfpppqplljwjfwfdwdzwddszddqzqnzzzwwlzzqvvjlllrwlrrmpmrmbmpplpspqsqmqcmcjjshsvstvvwtvthvvrfvvqmmjpmjmrjmmlvvnnrjjrcrwrhwwqzzvgvngncgcqqcffmfzfssbnbfblbggwhggmtgtvvqhhpttbcbczcjjbqbhqbhqbbccbhbqhbbmppdlpdllbvvdpvdvwvsvppllgblbttmcmtccbsswmswwwzfwfhhtfhthctchcfhhhfjjvhjhgjjjcwjwggrtgrttcqcwcswccfdffvpvtptprrvjjqvjjghggshhwmmcscmsmhmvmppprfrwwrhhvghhtnhncctbbbwzzbgzgdzgdzdpdvpvbbwgbwwrqwrqwqbqvvclccfcfzfdfrrthrrqcqddplpqlppbbfrfmmrmnnwhhgddmwwrzrsswpwhwdwhhsmhshqsqllbvlbllwbwbpwwfwmwsmspsvsdsbscctpctppvvpggtjjdmdqqgqbgglccvzcvcnczcgzgmzznpzzpcpnpvpcvvffrttqrqttflfbfjfnnwnlwlhwwqzqnqfftstdsttglldwwgqwwvqqzczfzdffbfsfssfwswdwnwdnnbcncwctttvsvjsvsrvvbtvvzhhvjvtvtjtsjjvhjjwpjjnzzpczzppgcpgcpgcgsgvsgvsscrcpcpspllzvzddpssssdpsddhffllzmzhzfhhdvhhvbvwwpwqpwqpwwmvwmvwvgvmvpvmpmrmzrrblltjtggvnggvppthhzjhzhffrvrhvrhrlrslsflfhhtvhvmmhppjgpjpcccmqcqvqhvvfssrtmnwjjslwhjgpvrwspjlwdwrmvfgwmplrmjrllndrjzvjfbwvzpjpfqrnjspwcpsgcvdlmfdfrvwdcvmbrnzncgnqlcvgqtpsbbpvprncdsgvpqbpcnffwqmmfsvnzspchhrlnzbhcdfdgtsllmqfbrcqwbmmzrfvsghjpmrndsdbqvtprmblnbvbnpvhtphbpjwdssvwgdzwztbpzdcsqzldjzrgcwhhspblrtncvntppcgttlflflnntcnzpbpgsclcjvbjhldcdzwjjhnfwzjmgcwtljhvbncwqnjhbrhfqcmnsdvntsbgnpqttzvbhzzpdznrhjpnsqzsztsblstbghlpwbmqjctlnqnttwshfvmjdhgbgjdhbzrfjqndrrhlqcmplczjtwpstlsmwwzqzmgvhsvjgbrtfwmvwlbhpccbqvmfmlgmbmbmldbcwmmhpnnbnffbnqgwhclgpzgbpjqvzmqhhhpltnwrdfrrnmlfrzflpnjztlnfzzzgmncprtblpsvrqgrnzbzfzhzhjjjdrnpvjpnwmlmlgvvtqmdvpnhvcrdmthcnnnvhnzmvgrtdvcthgjtvcgmtpsvmfztrflrrzbmcfhftwwcnjfpjtsnzjccmvdnrrwvbfjgcjttdvzncqhlqqphwphclztbhlqcfmnhcjmsscplnrsjqpdzrrzbthbcdnrzgdmstpgqqsvzclvmzjjdfqhhhttwcjtmwcbltghmslqvltqbjqqjpjvgntvnlttjcnhltflglgsmjwjjfldpfgjgrhttbwfhpsdbsmsfmfbtjlnhvjfqjrqhwdrcwpfthdgqzjjjfcvgdffrhvvwzfghpszmjjgscjvjnlgnbfbgfrbbzbzbnzngthrddfmsgsqqdddpfqwlchfblrvjdcgnzfzwmmnmvnzmpfmhbbhsbfdfclzcnbrlgpbsvfgfpshrpvpgccmmghphrcvzwnlqjcfwrtwvlvcsdldldvnpwgrcsqlftllcctnvcwbdswvqlzwzzbpmvvctcrgnjfstbqvnzczrjlljfqzrwtfwmlvvdfbfntrrljtbrtbdfsqpnppfbppbsmghbnqddhrvwmgzttnqjrqlfrdhqjndmnjlbctgclltmznmrqtfjsjwnztdvhnhlfwpnnqlhhsrfzglsnrdnfvrqssbtlthzfnjdvrcgzsbnpdgqhhrlwspfqfqpvzdfwgrlhwplzvbzprsqzcwvhggvzpgjztnvwvddsflgsvqljmmhhdzqsqmthwzvllqwmsnvdpdbjcgdtrsnmwhnzhbhgjssstmhrpssnhnntmrbbbjgmjqtncbdljcgtmbctpgdrnqcnrpssrdtpbsmlzlcztbrggglswnjzqgbsmgbqdzppqrwgtnlrjrvlpnqlcdwhltzzlqdwwrglldzcqrjtjtlgdqrtwzjgtdthsdccsmsrbjjsgdqcwdltvnjwtddsnpnsvzcdbfqnvsjbngqrztmbrnbvhhjzdtqrgldpvjqjpnshbjdsdgbjdjzdmrvzhwmtgcjrfnprstqgfgnwfpcjzhlnwpdbtqbspssqdrzhmmsrqtlwngvbrvgdgztnrlwcnqwvcdmhhdrmpfqbgbjpvzwbsbgcpsnpjplcrjdhflqvsdctclqqnmprngtvbmlmpqrsqdsrzgsmzmsczpsnmfmtfnjvnddjhqbjdvtgftjfvjhgpjqdhlszqjmcbnwrppzwjvmgblspjmfhjdbnmrllnfqlpcbndvqdzhhmmrpsljgdshpnrgnmwfjsdncqcwlctccrqghfdbsqqbnwctcqpvlrqqqvdjwlcnzmvdmcvlwnftjnqqldfwhmdtcpnlgfcdjdrfvmwqdzsjzctmmmrswhlwthttvcsqqscdcsmjgqfjhswlpsfjrppdmbwrthcwszqwwgnjsdqdrswmnzbrvqcwlrlwwvjmrrhsnzprggbzhhdqwvnspsmzzqdtbphzvwrzvqnbntjndrwllzwchczdwvnfjjdwfhdlgncftldzwdtjzjrmnfwwgmqdrltmgrfsjztfcvwjsggtvbnsvthflwfdtljrgqhmfqhmhfffqhtgwtlmwgzsglqnfwnrnvgvbdgqjrqtsmgsmzdpffnnzwlpbqphqmgdzspfrdqlptwmfwlgnqqdhtbbjtfhllrhhdcszjtmrprzhzzlgjqbcnhzcmhzrsnmmrzztffrldthhfvwhgjhwmjfbdvnllfmlpdsldjnpcwlpbwqzdwbgjb

part 1 output: 1802
part 2 output: 3551

 */
private const val input = "qhbhzbzzfrzrbzzcjzjrrvcvrvqvvnggnngcgssswbblplrlflfnnnmmjppgddqndnrnlnccpfcfjcjvjdjqqqmhhmwhwmmsnsvsjvjnvjnvjvsjsmjsjccwcqwcqwqjqwjwmwbmmbzbsbvsslbsbbbntnvvphpqqvrrtbrtrfftppbggpzzfhfcfsfmssffmbmzzmqzzblzzzmwwnggjwgjwgjgpgmmjvvmcvmmcfchfhllwmlljqqldqdqttsgsvscsmsnsmstmtssvgsgddwdffbppwfpplhlchhhdvvdrrmttmptmmmjsmshmmmgqmgggzjgzzmwzwcwhchqqfpfvvbqvbqbrblrrmtmstmmjvmmdnmmzczdzpztppjhjjwzjjjtdjjpljpjcppjllsffhbffbhhgttqjqzzfzbzcbzcbcrrjjrwrgwwbcbpcccctrtqtfqqfjjpgpdgdfgfrggpjjljglgclcqcqmcqmmgjjllpmphpjjgfjjqrrbppwmpmccftctjtjgjtgggzffcggwzzzdjdzzlgzgjzzvqvppczzjnjvvfhhtwtttdwtdtvddpzpnpcnppmvmcmcsmstthctchcggtssdttvztvvldlfftqqbzzjttvzztppscctzccgmcmvmhhchcscbbshbssgwwthwhmwwcgwcwrrvrzvrzzzvhvdvmmprrdmrmfmrmbmjbbmqbmbqqhbbszsjjlqjljtjstshhgphpffdhhtggtgbbqcqgqccfffcpcbpbfppwqpqcclbbwdwsscpchhfpfmpfmflfnnmggwrrznnghgvhhghrrhwrrcschcscqcmcfcvvgzztjtqjjshspsqsmmjnnmttsshvhmmqfqzztbzttvhttmwttnqnfncfcpfflmllmtlmmphmhlmmltmtztcczhzbbfmmlglnnfpppqplljwjfwfdwdzwddszddqzqnzzzwwlzzqvvjlllrwlrrmpmrmbmpplpspqsqmqcmcjjshsvstvvwtvthvvrfvvqmmjpmjmrjmmlvvnnrjjrcrwrhwwqzzvgvngncgcqqcffmfzfssbnbfblbggwhggmtgtvvqhhpttbcbczcjjbqbhqbhqbbccbhbqhbbmppdlpdllbvvdpvdvwvsvppllgblbttmcmtccbsswmswwwzfwfhhtfhthctchcfhhhfjjvhjhgjjjcwjwggrtgrttcqcwcswccfdffvpvtptprrvjjqvjjghggshhwmmcscmsmhmvmppprfrwwrhhvghhtnhncctbbbwzzbgzgdzgdzdpdvpvbbwgbwwrqwrqwqbqvvclccfcfzfdfrrthrrqcqddplpqlppbbfrfmmrmnnwhhgddmwwrzrsswpwhwdwhhsmhshqsqllbvlbllwbwbpwwfwmwsmspsvsdsbscctpctppvvpggtjjdmdqqgqbgglccvzcvcnczcgzgmzznpzzpcpnpvpcvvffrttqrqttflfbfjfnnwnlwlhwwqzqnqfftstdsttglldwwgqwwvqqzczfzdffbfsfssfwswdwnwdnnbcncwctttvsvjsvsrvvbtvvzhhvjvtvtjtsjjvhjjwpjjnzzpczzppgcpgcpgcgsgvsgvsscrcpcpspllzvzddpssssdpsddhffllzmzhzfhhdvhhvbvwwpwqpwqpwwmvwmvwvgvmvpvmpmrmzrrblltjtggvnggvppthhzjhzhffrvrhvrhrlrslsflfhhtvhvmmhppjgpjpcccmqcqvqhvvfssrtmnwjjslwhjgpvrwspjlwdwrmvfgwmplrmjrllndrjzvjfbwvzpjpfqrnjspwcpsgcvdlmfdfrvwdcvmbrnzncgnqlcvgqtpsbbpvprncdsgvpqbpcnffwqmmfsvnzspchhrlnzbhcdfdgtsllmqfbrcqwbmmzrfvsghjpmrndsdbqvtprmblnbvbnpvhtphbpjwdssvwgdzwztbpzdcsqzldjzrgcwhhspblrtncvntppcgttlflflnntcnzpbpgsclcjvbjhldcdzwjjhnfwzjmgcwtljhvbncwqnjhbrhfqcmnsdvntsbgnpqttzvbhzzpdznrhjpnsqzsztsblstbghlpwbmqjctlnqnttwshfvmjdhgbgjdhbzrfjqndrrhlqcmplczjtwpstlsmwwzqzmgvhsvjgbrtfwmvwlbhpccbqvmfmlgmbmbmldbcwmmhpnnbnffbnqgwhclgpzgbpjqvzmqhhhpltnwrdfrrnmlfrzflpnjztlnfzzzgmncprtblpsvrqgrnzbzfzhzhjjjdrnpvjpnwmlmlgvvtqmdvpnhvcrdmthcnnnvhnzmvgrtdvcthgjtvcgmtpsvmfztrflrrzbmcfhftwwcnjfpjtsnzjccmvdnrrwvbfjgcjttdvzncqhlqqphwphclztbhlqcfmnhcjmsscplnrsjqpdzrrzbthbcdnrzgdmstpgqqsvzclvmzjjdfqhhhttwcjtmwcbltghmslqvltqbjqqjpjvgntvnlttjcnhltflglgsmjwjjfldpfgjgrhttbwfhpsdbsmsfmfbtjlnhvjfqjrqhwdrcwpfthdgqzjjjfcvgdffrhvvwzfghpszmjjgscjvjnlgnbfbgfrbbzbzbnzngthrddfmsgsqqdddpfqwlchfblrvjdcgnzfzwmmnmvnzmpfmhbbhsbfdfclzcnbrlgpbsvfgfpshrpvpgccmmghphrcvzwnlqjcfwrtwvlvcsdldldvnpwgrcsqlftllcctnvcwbdswvqlzwzzbpmvvctcrgnjfstbqvnzczrjlljfqzrwtfwmlvvdfbfntrrljtbrtbdfsqpnppfbppbsmghbnqddhrvwmgzttnqjrqlfrdhqjndmnjlbctgclltmznmrqtfjsjwnztdvhnhlfwpnnqlhhsrfzglsnrdnfvrqssbtlthzfnjdvrcgzsbnpdgqhhrlwspfqfqpvzdfwgrlhwplzvbzprsqzcwvhggvzpgjztnvwvddsflgsvqljmmhhdzqsqmthwzvllqwmsnvdpdbjcgdtrsnmwhnzhbhgjssstmhrpssnhnntmrbbbjgmjqtncbdljcgtmbctpgdrnqcnrpssrdtpbsmlzlcztbrggglswnjzqgbsmgbqdzppqrwgtnlrjrvlpnqlcdwhltzzlqdwwrglldzcqrjtjtlgdqrtwzjgtdthsdccsmsrbjjsgdqcwdltvnjwtddsnpnsvzcdbfqnvsjbngqrztmbrnbvhhjzdtqrgldpvjqjpnshbjdsdgbjdjzdmrvzhwmtgcjrfnprstqgfgnwfpcjzhlnwpdbtqbspssqdrzhmmsrqtlwngvbrvgdgztnrlwcnqwvcdmhhdrmpfqbgbjpvzwbsbgcpsnpjplcrjdhflqvsdctclqqnmprngtvbmlmpqrsqdsrzgsmzmsczpsnmfmtfnjvnddjhqbjdvtgftjfvjhgpjqdhlszqjmcbnwrppzwjvmgblspjmfhjdbnmrllnfqlpcbndvqdzhhmmrpsljgdshpnrgnmwfjsdncqcwlctccrqghfdbsqqbnwctcqpvlrqqqvdjwlcnzmvdmcvlwnftjnqqldfwhmdtcpnlgfcdjdrfvmwqdzsjzctmmmrswhlwthttvcsqqscdcsmjgqfjhswlpsfjrppdmbwrthcwszqwwgnjsdqdrswmnzbrvqcwlrlwwvjmrrhsnzprggbzhhdqwvnspsmzzqdtbphzvwrzvqnbntjndrwllzwchczdwvnfjjdwfhdlgncftldzwdtjzjrmnfwwgmqdrltmgrfsjztfcvwjsggtvbnsvthflwfdtljrgqhmfqhmhfffqhtgwtlmwgzsglqnfwnrnvgvbdgqjrqtsmgsmzdpffnnzwlpbqphqmgdzspfrdqlptwmfwlgnqqdhtbbjtfhllrhhdcszjtmrprzhzzlgjqbcnhzcmhzrsnmmrzztffrldthhfvwhgjhwmjfbdvnllfmlpdsldjnpcwlpbwqzdwbgjb"

fun main() {
    println("part 1: ${part1()}")
    println("part 2: ${part2()}")
}

private fun part1(): Int = findStartOfMessage(4)
private fun part2(): Int = findStartOfMessage(14)

fun findStartOfMessage(size: Int): Int {
    var buffer = input.substring(0 until size)
    for (i in buffer.length until input.length) {
        // shift buffer along by 1
        buffer = buffer.plus(input[i]).substring(1..size)
        // check for double char
        if (!buffer.containsDuplicate()) return i + 1
    }
    return 0
}

fun String.containsDuplicate(): Boolean {
    for(char in this) {
        if (this.split(char).size > 2) return true
    }
    return false
}