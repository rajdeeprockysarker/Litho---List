package com.example.rajdeeps.litho;

/**
 * Created by rajdeeps on 27-04-2017.
 */


import android.support.v4.util.Pools;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.ComponentLifecycle;
import com.facebook.litho.annotations.Prop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * @prop-required color int
 * @prop-required title java.lang.String
 * @prop-required subtitle java.lang.String
 */
public final class ListItem extends ComponentLifecycle {
    private static ListItem sInstance = null;

    private static final Pools.SynchronizedPool<Builder> mBuilderPool = new Pools.SynchronizedPool<Builder>(2);

    private MyComponent mSpec = new MyComponent();

    private ListItem() {
    }

    public static synchronized ListItem get() {
        if (sInstance == null) {
            sInstance = new ListItem();
        }
        return sInstance;
    }

    @Override
    protected ComponentLayout onCreateLayout(ComponentContext c, Component _abstractImpl) {
        ListItemImpl _impl = (ListItemImpl) _abstractImpl;
        ComponentLayout _result = (ComponentLayout) mSpec.onCreateLayout(
                (ComponentContext) c,
                (int) _impl.color,
                (String) _impl.title,
                (String) _impl.subtitle,
                (int) _impl.imgid
                );
        return _result;
    }

    private static Builder newBuilder(ComponentContext context, int defStyleAttr, int defStyleRes,
                                      ListItemImpl listItemImpl) {
        Builder builder = mBuilderPool.acquire();
        if (builder == null) {
            builder = new Builder();
        }
        builder.init(context, defStyleAttr, defStyleRes, listItemImpl);
        return builder;
    }

    public static Builder create(ComponentContext context, int defStyleAttr, int defStyleRes) {
        return newBuilder(context, defStyleAttr, defStyleRes, new ListItemImpl());
    }

    public static Builder create(ComponentContext context) {
        return create(context, 0, 0);
    }

    private static class ListItemImpl extends Component<ListItem> implements Cloneable {
        @Prop
        int color;

        @Prop
        String title;

        @Prop
        String subtitle;

        @Prop
        int imgid;

        private ListItemImpl() {
            super(get());
        }

        @Override
        public String getSimpleName() {
            return "ListItem";
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            ListItemImpl listItemImpl = (ListItemImpl) other;
            if (this.getId() == listItemImpl.getId()) {
                return true;
            }
            if (color != listItemImpl.color) {
                return false;
            }
            if (title != null ? !title.equals(listItemImpl.title) : listItemImpl.title != null) {
                return false;
            }
            if (subtitle != null ? !subtitle.equals(listItemImpl.subtitle) : listItemImpl.subtitle != null) {
                return false;
            }
            if (imgid != listItemImpl.imgid) {
                return false;
            }
            return true;
        }
    }

    public static class Builder extends Component.Builder<ListItem> {
        private static final String[] REQUIRED_PROPS_NAMES = new String[] {"color", "title", "subtitle","imgid"};

        private static final int REQUIRED_PROPS_COUNT = 3;

        ListItemImpl mListItemImpl;

        ComponentContext mContext;

        private BitSet mRequired = new BitSet(REQUIRED_PROPS_COUNT);

        private void init(ComponentContext context, int defStyleAttr, int defStyleRes,
                          ListItemImpl listItemImpl) {
            super.init(context, defStyleAttr, defStyleRes, listItemImpl);
            mListItemImpl = listItemImpl;
            mContext = context;
            mRequired.clear();
        }

        public Builder color(int color) {
            this.mListItemImpl.color = color;
            mRequired.set(0);
            return this;
        }



        public Builder title(String title) {
            this.mListItemImpl.title = title;
            mRequired.set(1);
            return this;
        }

        public Builder subtitle(String subtitle) {
            this.mListItemImpl.subtitle = subtitle;
            mRequired.set(2);
            return this;
        }
        public Builder imgid(int imgid) {
            this.mListItemImpl.imgid = imgid;
            mRequired.set(3);
            return this;
        }

        public Builder key(String key) {
            super.setKey(key);
            return this;
        }

        @Override
        public Component<ListItem> build() {
            if (mRequired != null && mRequired.nextClearBit(0) < REQUIRED_PROPS_COUNT) {
                List<String> missingProps = new ArrayList<String>();
                for (int i = 0; i < REQUIRED_PROPS_COUNT; i++) {
                    if (!mRequired.get(i)) {
                        missingProps.add(REQUIRED_PROPS_NAMES[i]);
                    }
                }
                throw new IllegalStateException("The following props are not marked as optional and were not supplied: " + Arrays.toString(missingProps.toArray()));
            }
            ListItemImpl listItemImpl = mListItemImpl;
            release();
            return listItemImpl;
        }

        @Override
        protected void release() {
            super.release();
            mListItemImpl = null;
            mContext = null;
            mBuilderPool.release(this);
        }
    }
}
