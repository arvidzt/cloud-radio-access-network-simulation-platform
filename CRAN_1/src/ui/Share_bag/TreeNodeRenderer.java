/*
 * 类名：TreeNodeRenderer
 * 功能：扩展SubstanceDefaultTreeCellRenderer创建自己的树节点渲染器
 */
package ui.Share_bag;

import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.pushingpixels.substance.api.renderers.SubstanceDefaultTreeCellRenderer;

public class TreeNodeRenderer extends SubstanceDefaultTreeCellRenderer{
    private static final long serialVersionUID = 8532405600839140757L;
    
//    // 數據庫圖標,頂層節點用
//    private static final Icon databaseIcon = new ImageIcon(TreeNodeRenderer.class
//            .getResource("/database.gif"));
    
    // 表圖標,第三層節點用
    private static final Icon tableIcon = new ImageIcon(TreeNodeRenderer.class
            .getResource("image"+File.separator +"LeafIcon.png"));
    
    // 表空間關閉狀態圖標,關閉狀態的第二層節點用
    private static final Icon tableSpaceCloseIcon = new ImageIcon(TreeNodeRenderer.class
            .getResource("image"+File.separator +"ClosedIcon.png"));
    
    // 表空間關閉狀態圖標,打開狀態的第二層節點用
    private static final Icon tableSpaceOpenIcon = new ImageIcon(TreeNodeRenderer.class
            .getResource("image"+File.separator +"OpenIcon.png"));
    
    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,
                                                  boolean sel,
                                                  boolean expanded,
                                                  boolean leaf,
                                                  int row,
                                                  boolean hasFocus){
         super.getTreeCellRendererComponent(tree,   
                                            value,
                                            sel,   
                                            expanded,   
                                            leaf,   
                                            row,   
                                            hasFocus);   
        // 取得節點
        DefaultMutableTreeNode node=(DefaultMutableTreeNode)value;
        
        // 取得路徑
        TreeNode[] paths = node.getPath();
        
        // 按路径层次赋予不同的图标
        if (paths.length == 3) {            
            setIcon(tableIcon);
        }else if(paths.length == 2){
            // 按展開情況再賦予不同的圖標
            if(expanded){
                setIcon(tableSpaceOpenIcon);
            }
            else{
                setIcon(tableSpaceCloseIcon);
            }
        }
//        else if(paths.length == 1){
//            setIcon(databaseIcon);
//        }
        
        return this;        
    }
}
