package com.example.openglhoho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class ExampleList extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ListView(this){{
            setAdapter(new AdapterAdapter<Example>(Example.examples()) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    Example example = entries.get(position);
                    View entryView = getLayoutInflater().inflate(R.layout.example, null);
                    TextView title = (TextView) entryView.findViewById(R.id.title);
                    title.setText(example.title);
                    TextView description = (TextView) entryView.findViewById(R.id.description);
                    description.setText(example.description);
                    ImageView icon = (ImageView) entryView.findViewById(R.id.icon);
                    icon.setImageResource(example.iconResource);
                    return  entryView;
                }
            });
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ExampleList.this, GLES20Activity.class);
                    intent.putExtra(GLES20Activity.EXAMPLE, position);
                    startActivity(intent);
                }
            });
        }});
    }
}

class Example {
    public int iconResource;
    public String title;
    public String description;

    Example(int iconResource, String title, String description) {
        this.iconResource = iconResource;
        this.title = title;
        this.description = description;
    }
    public static List<Example> examples(){
        return new ArrayList<Example>() {{
            add(new Example(0, "Cube", "Draw indexed vertexes. Draw texture. Use colors. Rotate by touch events."));
            add(new Example(0, "Donut", "Create triangle strip mesh."));
            add(new Example(0, "Particles", "A texture based star shower"));
        }};
    }
}
