package Collections_08;

import java.util.*;


public class ShowHand {
    //定义最大玩家数量
    private int PLAY_NUM = 5;
    //定义扑克牌的所有花色和数值
    private String[] types = {"方块", "草花", "红心", "黑桃"};
    private String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    //cards是一局游戏中剩下的卡牌
    private List<String> cards = new LinkedList<String>();
    //定义所有玩家
    private String[] players = new String[PLAY_NUM];
    //所有玩家的手牌
    private List<String>[] playersCards = new List[PLAY_NUM];

    /**
     * 初始化卡牌,共计52张
     */
    public void initCards() {
        for (int i = 0; i < this.types.length; i++) {
            for (int j = 0; j < this.values.length; j++) {
                cards.add(types[i] + values[j]);
            }
        }
        //随机序列
        Collections.shuffle(cards);
    }

    /**
     * 初始化玩家,为每个玩家分派用户名
     */
    public void initPlayer(String... names) {
        if (names.length > PLAY_NUM || names.length < 2) {
            System.out.println("玩家数量不对");
            return;
        } else {
            //初始化玩家用户名
            for (int i = 0; i < names.length; i++) {
                players[i] = names[i];
            }
        }
    }

    /**
     * 初始化玩家手中的卡牌,开始时每个游戏玩家的手牌数量为空
     */
    public void initPlayerCards() {
        for (int i = 0; i < this.players.length; i++) {
            if (players[i] != null && !players[i].equals("")) {
                this.playersCards[i] = new LinkedList<String>();
            }
        }
    }

    /**
     * 输出全部扑克牌
     */
    public void showAllCards() {
        for (String card : cards) {
            System.out.println(card);
        }
    }

    /**
     * 派发扑克牌
     *
     * @param first 最先派发玩家
     */
    public void deliverCard(String first) {
        //调用ArrayUtils工具类的search方法
        //查询指定元素在数组中的索引
        int firstPos = Arrays.binarySearch(players,first);
    }
}
