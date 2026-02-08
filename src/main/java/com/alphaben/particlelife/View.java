package com.alphaben.particlelife;

import com.alphaben.particlelife.Intializer.BorderInitializer;
import com.alphaben.particlelife.Intializer.CenterInitializer;
import com.alphaben.particlelife.Intializer.KerenlInitializer;
import com.alphaben.particlelife.Intializer.RandomInitializer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.Timer;

/**
 *
 * @author alphaben
 */
public class View extends javax.swing.JPanel implements ComponentListener{

    /**
     * Creates new form View
     */
    
    private   Environment env;  
 
    public View()
    {
       initComponents();
       init();
        setSize(1000, 1000);
        env.setSize(this.getSize());
       
        ControlPanel.setBackground(new Color(0,0,255,70));
        
        Timer timer = new Timer(60, (e)-> {
            
            long start = System.currentTimeMillis();
            
            for(ParticleGroup group: env.getAllGroups())
            {
               group.update();
            }

            this.repaint();
            this.validate();
            
           
            long end  = System.currentTimeMillis();
            System.err.printf("time %d ms\n", end - start);
        });
        
        timer.start();


   
    }
    
    private void init() {
       this.addComponentListener(this);
        this.env = new Environment(this.getSize());
       this.env.setCurrentIntializer(new RandomInitializer());

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InitializersGroup = new javax.swing.ButtonGroup();
        ControlPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jRadioRandom = new javax.swing.JRadioButton();
        jRadioKernel = new javax.swing.JRadioButton();
        jRadioBorder = new javax.swing.JRadioButton();
        JRadioCenter = new javax.swing.JRadioButton();

        setForeground(new java.awt.Color(255, 255, 255));

        ControlPanel.setBackground(new java.awt.Color(204, 204, 255));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("New Environment");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Add One Group");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setText("Initializer");

        InitializersGroup.add(jRadioRandom);
        jRadioRandom.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioRandom.setForeground(new java.awt.Color(255, 255, 255));
        jRadioRandom.setSelected(true);
        jRadioRandom.setText("Random");
        jRadioRandom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioRandomStateChanged(evt);
            }
        });

        InitializersGroup.add(jRadioKernel);
        jRadioKernel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioKernel.setForeground(new java.awt.Color(255, 255, 255));
        jRadioKernel.setText("Kernel");
        jRadioKernel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioKernelStateChanged(evt);
            }
        });

        InitializersGroup.add(jRadioBorder);
        jRadioBorder.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioBorder.setForeground(new java.awt.Color(255, 255, 255));
        jRadioBorder.setText("Border");
        jRadioBorder.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioBorderStateChanged(evt);
            }
        });
        jRadioBorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBorderActionPerformed(evt);
            }
        });

        InitializersGroup.add(JRadioCenter);
        JRadioCenter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JRadioCenter.setForeground(new java.awt.Color(255, 255, 255));
        JRadioCenter.setText("Center");
        JRadioCenter.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JRadioCenterStateChanged(evt);
            }
        });
        JRadioCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRadioCenterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ControlPanelLayout = new javax.swing.GroupLayout(ControlPanel);
        ControlPanel.setLayout(ControlPanelLayout);
        ControlPanelLayout.setHorizontalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ControlPanelLayout.createSequentialGroup()
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioRandom)
                        .addComponent(jRadioKernel)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jRadioBorder)
                    .addComponent(JRadioCenter))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        ControlPanelLayout.setVerticalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanelLayout.createSequentialGroup()
                .addComponent(jButton2)
                .addGap(0, 0, 0)
                .addComponent(jButton1)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioRandom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioKernel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioBorder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JRadioCenter))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 577, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 214, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        this.env = new Environment(this.getSize());
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         this.env.addNewGroup();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioBorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBorderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioBorderActionPerformed

    private void JRadioCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRadioCenterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JRadioCenterActionPerformed

    private void jRadioRandomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioRandomStateChanged
       if(jRadioRandom.isSelected())
       {
         this.env.setCurrentIntializer(new RandomInitializer());
       }
    }//GEN-LAST:event_jRadioRandomStateChanged

    private void jRadioKernelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioKernelStateChanged
           if(jRadioKernel.isSelected())
       {
           this.env.setCurrentIntializer(new KerenlInitializer());
       }
    }//GEN-LAST:event_jRadioKernelStateChanged

    private void jRadioBorderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioBorderStateChanged
          if(jRadioBorder.isSelected())
            {
                      this.env.setCurrentIntializer(new BorderInitializer());
            }
    }//GEN-LAST:event_jRadioBorderStateChanged

    private void JRadioCenterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_JRadioCenterStateChanged
      if( JRadioCenter.isSelected())
      {
                    this.env.setCurrentIntializer(new CenterInitializer());
      }
    }//GEN-LAST:event_JRadioCenterStateChanged

    @Override
    public void paint(Graphics g)
    {
       setBackground(new Color(0,0,0,0)); 
       super.paint(g); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        
        g.fillRect(0, 0, getWidth(), getHeight());
       for(ParticleGroup group: env.getAllGroups())
        {
            for(Particle particle: group.members)
            {
                Color src = particle.color;
                g.setColor(new Color(src.getRed(), src.getGreen(), src.getBlue(), 80));
                g.fillOval((int)particle.x-4, (int)particle.y-4, 8, 8);
                g.setColor(particle.color);
              
                int dim  = Math.clamp((long)(particle.velocityX + particle.velocityY) , 4,10);
                
                g.fillOval((int)particle.x - 2, (int)particle.y - 2, dim, dim);
               
           
            }
       }
        super.paintComponent(g);
    
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ControlPanel;
    private javax.swing.ButtonGroup InitializersGroup;
    private javax.swing.JRadioButton JRadioCenter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioBorder;
    private javax.swing.JRadioButton jRadioKernel;
    private javax.swing.JRadioButton jRadioRandom;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentResized(ComponentEvent e)
    {
         env.setSize(this.getSize());

    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {
     
    }
}

