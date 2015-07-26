package dmitry.shnurenko.cash.view.panels.center.graphics;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.XChartPanel;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;

/**
 * @author Dmitry Shnurenko
 */
@Component
final class GraphicViewImpl extends JPanel implements GraphicView {

    public GraphicViewImpl() {
        //TODO this is stub implementation need add real one
        double[] xData = new double[]{0.0, 1.0, 2.0};
        double[] yData = new double[]{2.0, 1.0, 0.0};

        // Create Chart
        Chart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

        XChartPanel panel = new XChartPanel(chart);

        add(panel);
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public JComponent getComponent() {
        return this;
    }
}
