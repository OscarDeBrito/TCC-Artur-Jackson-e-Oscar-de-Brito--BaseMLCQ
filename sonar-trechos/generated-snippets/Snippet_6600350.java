public class Snippet__6600350 {

    @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mSpinner = (Spinner) view.findViewById(R.id.spinner);
            mResult = (TextView) view.findViewById(R.id.result);
            mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
            mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            mSwitchBtn = (Switch) view.findViewById(R.id.switchBtn);

            mSeekBar.setOnSeekBarChangeListener(this);
            mRatingBar.setOnRatingBarChangeListener(this);
            mSpinner.setOnItemSelectedListener(this);
            mSwitchBtn.setOnCheckedChangeListener(this);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpinner.setAdapter(adapter);
            mSpinner.setSelection(2);


        }

}
